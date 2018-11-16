package cn.com.cslc.aw.core.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.com.cslc.aw.core.common.service.BaseService;

@RestController
public class BaseApiController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseApiController.class);
	
	@Autowired
	protected BaseService baseService;
		
}
