package cn.com.cslc.aw.core.common.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import cn.com.cslc.aw.core.common.constant.JRConstant;
import cn.com.cslc.aw.core.common.jasperreports.JRRequest;

public abstract class JasperReportsBaseController extends BaseController {
	
    @Autowired
    private DataSource dataSource;
    
	protected String getReportView(JRRequest jrRequest, ModelMap modelMap){
		modelMap.put(JRConstant.DATASOURCE, dataSource);
		modelMap.put(JRConstant.FILE_FORMAT, jrRequest.getReportFormat());
		//modelMap.putAll(jrRequest.getReportParamMap());
		return JRConstant.VIEW_NAME_PREFIX + jrRequest.getReportName();
	}

}