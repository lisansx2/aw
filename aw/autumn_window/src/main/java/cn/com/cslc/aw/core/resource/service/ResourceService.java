package cn.com.cslc.aw.core.resource.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.core.authority.service.AuthorityService;
import cn.com.cslc.aw.core.common.domain.SysResource;
import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.dto.MenuDto;
import cn.com.cslc.aw.core.resource.repository.ResourceRepository;

@Transactional(readOnly = true)
@Service
public class ResourceService {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private AuthorityService authorityService;
	
	public SysResource getRootResource(){
		return resourceRepository.findOne(new Specification<SysResource>(){
			@Override
			public Predicate toPredicate(Root<SysResource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("parentId"), 0);
			}
		});
	}
	
    public List<MenuDto> findMenus(SysUser user) {

        List<SysResource> resources = resourceRepository.findAllOrderedMenu();

        Set<String> userResourceIdentifierSet = authorityService.findResourceIdentifier(user);

        Iterator<SysResource> iter = resources.iterator();
       
        while (iter.hasNext()) {
            if (!hasResource(iter.next(), userResourceIdentifierSet)) {
                iter.remove();
            }
        }
        return convertToMenus(resources);
    }
    
    private boolean hasResource(SysResource resource, Set<String> userResourceIdentifierSet) {
        for (String userResourceIdentifier : userResourceIdentifierSet) {
            if (userResourceIdentifier.equals(resource.getIdentifier())) {
                return true;
            }
        }
        return false;
    }

    
    private  List<MenuDto> convertToMenus(List<SysResource> resources) {

        if (resources.isEmpty()) {
            return new ArrayList<MenuDto>();
        }

        MenuDto root = new MenuDto();
        SysResource rootResource = this.getRootResource();
        root.setId(rootResource.getId());
        recursiveMenu(root, resources);
        
        List<MenuDto> menus = root.getChildren();
        //removeNoLeafMenu(menus);

        return menus;
    }

    private static MenuDto convertToMenu(SysResource resource) {
    	MenuDto menu = new MenuDto();
    	menu.setId(resource.getId());
    	menu.setName(resource.getName());
    	menu.setUrl(resource.getUrl());
    	menu.setIcon(resource.getIcon());
        return menu;
    }
    
    private static void recursiveMenu(MenuDto menu, List<SysResource> resourceList) {
    	List<SysResource> removedResourceList = Lists.newArrayList();
        for (SysResource resource : resourceList) {
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                removedResourceList.add(resource);
            }
        }
        if(!removedResourceList.isEmpty()){
        	resourceList.removeAll(removedResourceList);
        }

        for (MenuDto subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resourceList);
        }
    }
    
    private static void removeNoLeafMenu(List<MenuDto> menuList) {
        if (menuList.isEmpty()) {
            return;
        }
        for (MenuDto menu : menuList) {
            removeNoLeafMenu(menu.getChildren());
            if (!menu.isHasChildren() && StringUtils.isEmpty(menu.getUrl())) {
            	menuList.remove(menu);
            }
        }
    }	
}
