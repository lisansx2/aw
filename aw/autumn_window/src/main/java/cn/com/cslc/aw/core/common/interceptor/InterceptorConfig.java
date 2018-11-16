package cn.com.cslc.aw.core.common.interceptor;

import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.dto.LogOperationAwDto;
import cn.com.cslc.aw.core.common.service.LogOperationAwService;
import cn.com.cslc.aw.core.common.thread.LogAwCallable;
import cn.com.cslc.aw.core.common.utils.SpringContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Component
public class InterceptorConfig implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);
    private static final String pattern = "(*)";

    private LogOperationAwService logOperationAwService;

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        this.writeLogAwInDB(httpServletRequest, "preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        this.writeLogAwInDB(httpServletRequest, "postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        this.writeLogAwInDB(httpServletRequest, "afterCompletion");
    }

    private void writeLogAwInDB(HttpServletRequest httpServletRequest, String fromMethod){

        String requestUrl;
        StringBuffer requestParam;
        StringBuffer logDesc;
        String provinceNo;
        String requestId = null;
        Date invokeTime = null;
        Integer logTypeCode = null;
        Integer userId = null;
        String userName = null;
        Long costTime = null;
        String userIp;
        String serverIp;

        try{
            if("preHandle".equals(fromMethod)){
                logTypeCode = Integer.valueOf(10);
                requestId = UUID.randomUUID().toString().replace("-", "");
                invokeTime = new Date();
                httpServletRequest.setAttribute("logAw_UUID", requestId );
                httpServletRequest.setAttribute("logAw_invokeTime", invokeTime );
            }

            if("postHandle".equals(fromMethod)){
                logTypeCode = Integer.valueOf(20);
                requestId = httpServletRequest.getAttribute("logAw_UUID").toString();
                invokeTime = (Date) httpServletRequest.getAttribute("logAw_invokeTime");
                costTime = new Date().getTime() - invokeTime.getTime();
            }

            if("afterCompletion".equals(fromMethod)){
                logTypeCode = Integer.valueOf(30);
                requestId = httpServletRequest.getAttribute("logAw_UUID").toString();
                invokeTime = (Date) httpServletRequest.getAttribute("logAw_invokeTime");
                costTime = new Date().getTime() - invokeTime.getTime();
            }


            SysUser sysUser = (SysUser) httpServletRequest.getSession().getAttribute("currentUser");
            provinceNo = sysUser.getSysProvince().getProvinceNo();
            userId = sysUser.getId().intValue();
            userName = sysUser.getUserName();
            userIp = getClientIpAddr(httpServletRequest);
            serverIp = InetAddress.getLocalHost().getHostName();
            requestUrl = httpServletRequest.getRequestURI();
            logDesc = new StringBuffer("REQUESTID:").append(requestId).append(pattern);
            logDesc.append("URL:").append(requestUrl).append(pattern);

            requestParam = new StringBuffer();
            Map paramMap = (Map) httpServletRequest.getParameterMap();
            requestParam.append("参数:");
            if(paramMap != null && paramMap.size() > 0){
                Iterator iterator = paramMap.entrySet().iterator();
                while(iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = (String) entry.getKey();
                    String[] val = (String[]) entry.getValue();
                    if(val != null && val.length > 0 && val[0] != null && val[0].trim().length() > 0) {
                        String value = val[0];
                        requestParam.append(key).append("=").append(value).append(";");
                    }
                }
            }

            LogOperationAwDto logOperationAwDto = new LogOperationAwDto();
            logOperationAwDto.setLogTypeCode(logTypeCode);
            logOperationAwDto.setRequestUrl(replaceForbiddenCharactors(requestUrl));
            logOperationAwDto.setRequestParam(replaceForbiddenCharactors(requestParam.toString()));
            logOperationAwDto.setLogDesc(replaceForbiddenCharactors(logDesc.toString()));
            logOperationAwDto.setUserId(userId);
            logOperationAwDto.setUserName(userName);
            logOperationAwDto.setUserIp(userIp);
            logOperationAwDto.setServerIp(serverIp);
            logOperationAwDto.setProvinceNo(provinceNo);
            logOperationAwDto.setCostTime(costTime);



            logOperationAwService = (LogOperationAwService) SpringContextAware.getBean("logOperationAwService");
            ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringContextAware.getBean("taskExecutor");

            taskExecutor.submit(new LogAwCallable(logOperationAwDto, logOperationAwService));

        }catch(Exception ex){
            ex.printStackTrace();
            log.error("writeLogAwInDB Error", ex);
        }

    }

    private String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            String[] ips = ip.split(",");
            String realIp = ips[0];
            if(realIp != null && !realIp.trim().equals("")) {
                ip = realIp.trim();
            }
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 本地测试localhost登录时，ip地址为：0:0:0:0:0:0:0:1
        if(ip != null && ip.trim().startsWith("0:0")) {
            ip = "localhost";
        }
        return ip;
    }

    private String replaceForbiddenCharactors(String str) {
        if(str == null) {
            return "";
        }
        str = str.replaceAll("'", "''");
        return str;
    }

}
