package cn.com.cslc.aw.core.org.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.domain.SysOrgAgent;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysOrgManage;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeViewNodeState;
import cn.com.cslc.aw.core.common.exception.BusinessLogicException;
import cn.com.cslc.aw.core.org.dto.OrgAuthResult;
import cn.com.cslc.aw.core.org.dto.QueryOrgCondition;
import cn.com.cslc.aw.core.org.repository.OrgAgentRepository;
import cn.com.cslc.aw.core.org.repository.OrgCustomerRepository;
import cn.com.cslc.aw.core.org.repository.OrgManageRepository;
import cn.com.cslc.aw.core.org.repository.OrgManageRepositoryImpl;
import cn.com.cslc.aw.core.org.repository.OrgRepository;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.domain.SysOrgCustomer;

@Transactional(readOnly = true)
@Service
public class OrgService {
	
	@Autowired
	private OrgRepository orgRepository;
	
	@Autowired
	private	OrgManageRepository orgManageRepository;
	
	@Autowired
	private OrgAgentRepository orgAgentRepository;
	
	@Autowired
	private OrgCustomerRepository orgCustomerRepository;
	
	@Autowired
	private OrgManageRepositoryImpl orgManageRepositoryImpl;
		
	public SysOrg getRootOrg(){
		return orgRepository.findOne(new Specification<SysOrg>(){
			@Override
			public Predicate toPredicate(Root<SysOrg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isNull(root.get("parentId"));
			}
		});
	}
	
	/**
	 * 获取
	 * @param orgId
	 * @return
	 */
    public List<BootstrapTreeView> findAllOrgTree() {

        List<SysOrg> orgs = orgRepository.findAllOrderedOrg();

        return convertToTree(orgs);
    }
	
    public List<BootstrapTreeView> findOrgTreeByCode(String orgCode, Boolean selected) {
    	List<BootstrapTreeView> bootstrapTreeViewList = Lists.newArrayList(); 
    	SysOrg rootOrg = orgRepository.findByCode(orgCode);
    	BootstrapTreeView bootstrapTreeRoot = convertToTree(rootOrg, selected);
    	this.recursionSysOrg(bootstrapTreeRoot,Lists.newArrayList(rootOrg.getChildren()));
    	bootstrapTreeViewList.add(bootstrapTreeRoot);
        return bootstrapTreeViewList;
    }
    
    public List<BootstrapTreeView> findOrgTreeByCodes(Boolean selected, String... orgCodes) {
    	List<BootstrapTreeView> bootstrapTreeViewList = Lists.newArrayList(); 
    	for(String orgCode : orgCodes){
    		bootstrapTreeViewList.add(this.findOrgTreeByCode(orgCode,selected).get(0));
    	}
    	return bootstrapTreeViewList;
    }
    
	public List<BootstrapTreeView> findOrgTreeByCodes(String... orgCodes) {
		return this.findOrgTreeByCodes(Boolean.FALSE, orgCodes);
	}
    
    private void recursionSysOrg(BootstrapTreeView treeNode, List<SysOrg> orgChildren){
    	Collections.sort(orgChildren);
		for(SysOrg childOrg : orgChildren){
        	if(treeNode.getNodes() == null){
        		treeNode.setNodes(Lists.newArrayList());
        	}
        	if(childOrg.getDelFlag() == null || childOrg.getDelFlag() == 0){
            	BootstrapTreeView childNode = convertToTree(childOrg,Boolean.FALSE);
            	treeNode.getNodes().add(childNode);	
            	recursionSysOrg(childNode,Lists.newArrayList(childOrg.getChildren()));
        	}
		}
    }
    
    private  List<BootstrapTreeView> convertToTree(List<SysOrg> orgs) {

        if (orgs.isEmpty()) {
            return  Lists.newArrayList();
        }

        BootstrapTreeView root = new BootstrapTreeView();
        SysOrg rootOrg = this.getRootOrg();
        root.setId(rootOrg.getId());
        root.setText(rootOrg.getCode() + "  " + rootOrg.getName());
        recursiveTree(root, orgs);
        List<BootstrapTreeView> treeList = Lists.newArrayList();
        treeList.add(root);
        return treeList;
    }

    
    private static BootstrapTreeView convertToTree(SysOrg org, Boolean selected) {
    	BootstrapTreeView tree = new BootstrapTreeView();
    	tree.setId(org.getId());
    	tree.setCode(org.getCode());
    	tree.setText(org.getCode() + "  " + org.getName());
    	BootstrapTreeViewNodeState state = new BootstrapTreeViewNodeState();
    	state.setSelected(selected);
    	tree.setState(state);
        return tree;
    }
    
    private static void recursiveTree(BootstrapTreeView treeNode, List<SysOrg> sysOrgList) {
    	List<SysOrg> removedOrgList = Lists.newArrayList();
        for (SysOrg org : sysOrgList) {
            if (org.getParentCode().equals(treeNode.getText().split("  ")[0])) {
            	if(treeNode.getNodes() == null){
            		treeNode.setNodes(Lists.newArrayList());
            	}
            	treeNode.getNodes().add(convertToTree(org, Boolean.FALSE));
            	removedOrgList.add(org);
            }
        }
        if(!removedOrgList.isEmpty()){
        	sysOrgList.removeAll(removedOrgList);
        }

        if(treeNode.getNodes() != null){
            for (BootstrapTreeView subTreeNode : treeNode.getNodes()) {
                recursiveTree(subTreeNode, sysOrgList);
            }
        }
    }
    
	public SysOrg findOrgByCode(String code) {
		return orgRepository.findByCode(code);
	}
	
	public List<SysAgent> findOrgAgent(List<SysOrg> orgList){
		List<SysAgent> agentList = Lists.newArrayList();
		List<Long> orgIdList = Lists.newArrayList();
		for(SysOrg org : orgList){
			orgIdList.add(org.getId());
		}
		List<SysOrgAgent> orgAgentList = orgAgentRepository.findOrgAgent(orgIdList);
		for(SysOrgAgent orgAgent : orgAgentList){
			agentList.add(orgAgent.getSysAgent());
		}
		
		return  new ArrayList<SysAgent>(new HashSet<SysAgent>(agentList));
	}

	public List<SysProvince> findOrgProvince(List<SysOrg> orgList) {
		List<Long> orgIdList = Lists.newArrayList();
		for(SysOrg org : orgList){
			orgIdList.add(org.getId());
		}
		List<SysProvince> orgProvinceList = orgRepository.findOrgProvinceByIds(orgIdList);
		return orgProvinceList;
	}
	
	public List<SysCustomer> findOrgCustomer(List<SysOrg> orgList){
		List<SysCustomer> customerList = Lists.newArrayList();
		List<Long> orgIdList = Lists.newArrayList();
		for(SysOrg org : orgList){
			orgIdList.add(org.getId());
		}
		List<SysOrgCustomer> orgCustomerList = orgCustomerRepository.findOrgCustomer(orgIdList);
		for(SysOrgCustomer orgCustomer : orgCustomerList){
			customerList.add(orgCustomer.getSysCustomer());
		}
		return  new ArrayList<SysCustomer>(new HashSet<SysCustomer>(customerList));
	}
	
	
	public BootstrapTreeView recursiveTreeAndSetSelectedOrg(BootstrapTreeView node, String orgCode){
		
		String treeOrgCode = node.getText().split("  ")[0];
		if(orgCode.equals(treeOrgCode)){
			node.getState().setSelected(Boolean.TRUE);
		}
		if(node.getNodes() != null && !node.getNodes().isEmpty()){
			for(BootstrapTreeView child : node.getNodes()){
				this.recursiveTreeAndSetSelectedOrg(child, orgCode);
			}
		}
		return node;
	}
	
	/**
	 * 获取该机构下的所有机构，包括该机构
	 * @return
	 */
	public Set<SysOrg> findOrgsByOrgCode(String	orgCode){
		SysOrg rootOrg = this.findOrgByCode(orgCode);
		Set<SysOrg> orgSet = Sets.newHashSet();
		this.recursiveOrg(rootOrg, orgSet);
		return orgSet;
	}
	
	private void recursiveOrg(SysOrg node, Set<SysOrg> orgSet){
		node.getSysProvince();
		orgSet.add(node);
		if(!node.getChildren().isEmpty()){
			for(SysOrg org : node.getChildren()){
				recursiveOrg(org,orgSet);
			}
		}else{
			return;
		}
	}
	
	/**
	 * 获取该机构下的所有机构的省，包括该机构
	 * @return
	 */
	public Set<SysProvince> findProvinceByOrgCode(String orgCode){
		Set<SysOrg> orgSet = this.findOrgsByOrgCode(orgCode);
		Set<SysProvince> provinceSet = Sets.newHashSet();
		for(SysOrg org : orgSet){
			provinceSet.add(org.getSysProvince());
		}
		return provinceSet;
	}
	
	public List<SysOrg> findAllSuperMarketOrgs(){
		return orgRepository.findAllSuperMarketOrgs();
	}

	public Page<OrgAuthResult> queryOrgsByCondition(QueryOrgCondition queryOrgCondition,Pageable pageable) {
		return orgManageRepositoryImpl.queryByCondition(queryOrgCondition, pageable);
	}
	
	public String getOrgCodeNext(String code) {
		return orgManageRepositoryImpl.getOrgCodeNext(code);
	}

	public SysOrgManage findByOrgId(Long orgId) {
		Assert.notNull(orgId, "orgId不允许为空");
		return orgManageRepository.findOne(orgId);
	}
	
	public SysOrg findOneById(Long orgId) {
		Assert.notNull(orgId, "orgId不允许为空");
		return orgRepository.findOne(orgId);
	}

	@Transactional
	public SysOrgManage updateOrg(SysOrgManage org) {
		SysOrgManage orgdat = null;
		SysOrgManage updateOrg = orgManageRepository.findOne(org.getId());
		//SysOrgManage parentOrg = orgManageRepository.findOne(org.getParentId()==null?-1:org.getParentId());
		
		//updateOrg.setCode(org.getCode());
		if(updateOrg != null){
			updateOrg.setName(org.getName());
			updateOrg.setUpdateTime(new Date());
			orgdat = orgManageRepository.save(updateOrg);
		}

		//updateOrg.setOrgTypeId(org.getOrgTypeId());
		//updateOrg.setParentId(org.getParentId());
//		if(parentOrg!=null) {
//			updateOrg.setProvinceId(parentOrg.getProvinceId());
//			updateOrg.setParentCode(parentOrg.getCode());
//			updateOrg.setParentIds(parentOrg.getParentIds()+"/"+parentOrg.getId());
//		}
		
		return orgdat;
		
	}
	@Transactional
	public SysOrgManage delOrg(Long orgId) {
		SysOrgManage org = null;
		SysOrgManage updateOrg = orgManageRepository.findOne(orgId);
		if(updateOrg != null){
			updateOrg.setDelFlag(Long.valueOf(1));
			updateOrg.setDelTime(new Date());
			org = orgManageRepository.save(updateOrg);
		}
		if(updateOrg.getParentId() != null){
			 if(orgManageRepositoryImpl.getOrgSumByParentId(updateOrg.getParentId())==0){
				 SysOrgManage updateParentOrg = orgManageRepository.findOne(updateOrg.getParentId());
				 if(updateParentOrg != null){
					 updateParentOrg.setHasChildren((short)0);
					 orgManageRepository.save(updateParentOrg);
				 }
			 }
		}
		 return org;
	}

	public SysOrgManage findByOrgName(String name) {
		return orgManageRepository.findByName(name);
	}

	@Transactional
	public SysOrgManage addOrg(SysOrgManage org) {
		SysOrgManage orgdat = null;
		SysOrgManage updateOrg = new SysOrgManage();
		SysOrgManage parentOrg = orgManageRepository.findOne(org.getParentId()==null?-1:org.getParentId());
		updateOrg.setCode(org.getCode());
		updateOrg.setName(org.getName());
		updateOrg.setOrgTypeId(org.getOrgTypeId());
		updateOrg.setParentId(org.getParentId());
		updateOrg.setIsShow((short) 1);
		updateOrg.setHasChildren((short) 0);
		updateOrg.setUpdateTime(new Date());
		updateOrg.setDelFlag(Long.valueOf(0));
		updateOrg.setProvinceId(org.getProvinceId());
		if(parentOrg!=null) {
			parentOrg.setHasChildren((short)1);
			orgManageRepository.save(parentOrg);
//			updateOrg.setProvinceId(parentOrg.getProvinceId());
			updateOrg.setParentCode(parentOrg.getCode());
			updateOrg.setParentIds(parentOrg.getParentIds()+"/"+parentOrg.getId());
		}
		orgdat = orgManageRepository.save(updateOrg);
		return orgdat;
	}

}
