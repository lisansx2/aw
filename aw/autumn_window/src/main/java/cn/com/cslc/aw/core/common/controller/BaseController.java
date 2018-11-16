package cn.com.cslc.aw.core.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.cslc.aw.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.core.common.service.BaseService;

@Controller
public abstract class BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected BaseService baseService;
	
	@ExceptionHandler(BaseApplicationException.class)  
    protected String runtimeExceptionHandler(BaseApplicationException exception,  
    		RedirectAttributes model) {
		LOG.error(exception.getLocalizedMessage());  
		model.addFlashAttribute("success", Boolean.FALSE);
		model.addFlashAttribute("exception", exception);
        return getIndexViewName();  
    }
	protected abstract String getIndexViewName();
	
}
