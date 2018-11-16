package cn.com.cslc.aw.tools.resource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.tools.core.common.dto.MenuDto;
import cn.com.cslc.aw.tools.core.config.AppConfig;

@Transactional(readOnly = true)
@Service
public class ResourceService {

	@Autowired
	private AppConfig appConfig;
	
    public List<MenuDto> findMenus() {
    	List<MenuDto> menus = new ArrayList<MenuDto>();
		for(Map<String, String> menuMap : appConfig.getMenuDef()){
	        MenuDto menu = new MenuDto();
	        menu.setIcon(menuMap.get("icon"));
	        menu.setName(menuMap.get("name"));
	        menu.setUrl(menuMap.get("url"));
	        menus.add(menu);
		}
        return menus;
    }
}
