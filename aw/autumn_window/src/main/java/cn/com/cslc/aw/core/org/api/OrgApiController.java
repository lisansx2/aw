package cn.com.cslc.aw.core.org.api;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysOrgManage;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.dto.BootstrapTreeView;
import cn.com.cslc.aw.core.org.dto.OrgAuthResult;
import cn.com.cslc.aw.core.org.dto.QueryOrgCondition;
import cn.com.cslc.aw.core.org.service.OrgService;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;

/**
 * 机构管理
 * @author liuxuewen
 * 2018-7-3
 */
@RestController
@RequestMapping("/api/org")
public class OrgApiController extends BaseApiController{
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value="/getOrgCodeNext/{code}",method = RequestMethod.GET, produces="application/json")
	public String  getOrgCodeNext(@PathVariable String code){
		return orgService.getOrgCodeNext(code);
	}
	
	@RequestMapping(value="/orgDelCheck/{id}",method=RequestMethod.GET,produces="application/json")
	public int checkOrgAuth(@PathVariable Long id){
		SysOrg  sysOrg= orgService.findOneById(id);
		if(sysOrg.getSysOrgCustomers().size()>0 || sysOrg.getSysOrgAgents().size()>0){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value="/orgDelCheck1/{id}",method=RequestMethod.GET,produces="application/json")
	public int checkOrgAuth1(@PathVariable Long id){
		SysOrg  sysOrg= orgService.findOneById(id);
		if(sysOrg.getSysUserOrgs().size()>0){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value = "/search", method = POST)
	public DataTablesResponse<List<OrgAuthResult>> queryOrgs(@DataTablesRequestParams DataTablesRequest dataTablesRequest,
			QueryOrgCondition queryOrgCondition,
			@SessionAttribute Set<String> userAgentCodeSet) {
		Page<OrgAuthResult> page = orgService.queryOrgsByCondition(queryOrgCondition,dataTablesRequest.getPageRequest());
		
		return generateDTResponse(dataTablesRequest, page);
	}

}
