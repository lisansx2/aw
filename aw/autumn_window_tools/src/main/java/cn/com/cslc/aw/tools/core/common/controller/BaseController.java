package cn.com.cslc.aw.tools.core.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.com.cslc.aw.tools.core.common.exception.BaseApplicationException;
import cn.com.cslc.aw.tools.core.common.exception.BaseException;

@Controller
public abstract class BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	@ExceptionHandler(BaseException.class)  
    protected String runtimeExceptionHandler(BaseApplicationException exception,  
    		Model model) {
		LOG.error(exception.getLocalizedMessage());  
		model.addAllAttributes(exception.getModel().asMap());
		model.addAttribute("success", Boolean.FALSE);
		model.addAttribute("exception", exception);
        return getIndexViewName();  
    }
	protected abstract String getIndexViewName();
	
}
