package cn.com.cslc.aw.core.common.jasperreports;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.com.cslc.aw.core.common.constant.JRConstant;

public class JRRequestParamsArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    return parameter.getParameterAnnotation(JRRequestParams.class) != null;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest httpRequest = (HttpServletRequest) webRequest.getNativeRequest();
    JRRequest jrRequest = new JRRequest();
    String servletPath = httpRequest.getServletPath();
    String reportName = httpRequest.getServletPath().substring("/reports/".length(), servletPath.length()-1).split("/")[0];
    jrRequest.setReportName(reportName);
    
    Map<String, String[]> paramsMap =  httpRequest.getParameterMap();
    for(Entry<String, String[]> paramEntry : paramsMap.entrySet()){
    	String paramKey = paramEntry.getKey();
    	String paramValue = paramEntry.getValue()[0];
    	if(paramKey.equals(JRConstant.FILE_FORMAT)){
    		jrRequest.setReportFormat(paramValue);
    	}else{
    		jrRequest.getReportParamMap().put(paramKey, paramValue);
    	}
    }
    return jrRequest;
  }
}
