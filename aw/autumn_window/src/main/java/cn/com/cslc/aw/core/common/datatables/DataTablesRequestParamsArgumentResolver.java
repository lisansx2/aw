package cn.com.cslc.aw.core.common.datatables;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DataTablesRequestParamsArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    return parameter.getParameterAnnotation(DataTablesRequestParams.class) != null;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest httpRequest = (HttpServletRequest) webRequest.getNativeRequest();
    DataTablesRequest tablesRequest = new DataTablesRequest();

    tablesRequest.setDraw(Integer.valueOf(httpRequest.getParameter("draw")));
    tablesRequest.setStart(Integer.valueOf(httpRequest.getParameter("start")));
    tablesRequest.setLength(Integer.valueOf(httpRequest.getParameter("length")));
    
    Set<Entry<String, String[]>> paramsEntrySet = httpRequest.getParameterMap().entrySet();
    Iterator<Entry<String, String[]>> paramsEntrySetIter = paramsEntrySet.iterator();
    while(paramsEntrySetIter.hasNext()){
    	Entry<String, String[]> paramEntry = paramsEntrySetIter.next();
    	String paramKey = paramEntry.getKey();
    	
    	if(paramKey.indexOf("order[") != -1 ){
    		Integer orderIndex = Integer.parseInt(paramKey.substring(paramKey.indexOf("[")+1, paramKey.indexOf("]")));
    		if(tablesRequest.getOrderMap().get(orderIndex) == null){
    			Map<String,String> orderMap = new HashMap<String,String>();
    			tablesRequest.getOrderMap().put(orderIndex, orderMap);
    		}
    		
    		if(paramKey.indexOf("[column]") != -1 ){
    			String orderColumIndex =  httpRequest.getParameter(paramKey);
    			String orderColumn = httpRequest.getParameter("columns[" + orderColumIndex + "][data]");
    			tablesRequest.getOrderMap().get(orderIndex).put("orderColumn", orderColumn);
    		};
    		
    		if(paramKey.indexOf("[dir]") != -1 ){
    			String orderDir = httpRequest.getParameter(paramKey);
    			tablesRequest.getOrderMap().get(orderIndex).put("orderDirection", orderDir);
    		};
    	}
    }
    
    return tablesRequest;
  }

}
