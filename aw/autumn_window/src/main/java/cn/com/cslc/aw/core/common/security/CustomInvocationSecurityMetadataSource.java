package cn.com.cslc.aw.core.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import cn.com.cslc.aw.core.common.domain.SysResource;
import cn.com.cslc.aw.core.resource.repository.ResourceRepository;

@Component
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();  
	
	private Map<RequestMatcher, Collection<ConfigAttribute>> urlAuthorityMap;  
	
	private RequestMatcher ignoreRequestMatcher;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	private static Logger LOG = LoggerFactory.getLogger(CustomInvocationSecurityMetadataSource.class);
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String url = filterInvocation.getRequestUrl();
		Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( userPrincipal instanceof UserDetails){
			LOG.info("用户（" + ((UserDetails)userPrincipal).getUsername() + "）正在访问资源" + url);
		}else{
			LOG.info("用户（" + userPrincipal.toString() + "）正在访问资源" + url);
		}
		
		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;  
		final HttpServletRequest request = filterInvocation.getRequest();  
		
		boolean resourceDefined = false;
		
		 for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : urlAuthorityMap.entrySet()) {  
	            if (entry.getKey().matches(request)) {
	            	resourceDefined = true;
	                attrs =  entry.getValue();  
	                break;  
	            }  
	        }  
		 
		 if(!ignoreRequestMatcher.matches(request)){
			 if(!resourceDefined){
			 		LOG.info("没有定义此资源，所有用户均无法访问！"  + url);
		 			throw new AccessDeniedException("没有权限访问此资源！" + url);
			 }
			 	if(attrs.isEmpty()){
			 		LOG.info("没有定义此资源的权限，所有用户均无法访问！"  + url);
		 			throw new AccessDeniedException("没有权限访问此资源！" + url);
			 	}
		 }
	     
		 return attrs;  
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : urlAuthorityMap.entrySet()) {  
            allAttributes.addAll(entry.getValue());  
        }  
        return allAttributes; 
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return true;
	}

	
	@PostConstruct
    protected void loadRequestAuthorityMap(){  
    	this.urlAuthorityMap =   
                new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();  
    	List<SysResource> resourceList = resourceRepository.findAll(new Specification<SysResource>(){
			@Override
			public Predicate toPredicate(Root<SysResource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch("sysRoleResource",JoinType.LEFT);
				Predicate hasChildrenPredicate = cb.notEqual(root.get("hasChildren"), 1);
				Predicate urlPredicate = cb.isNotNull(root.get("url"));
				query.distinct(true);
				return cb.and(hasChildrenPredicate, urlPredicate);
			}
    	});
    	
        for(SysResource resource: resourceList){
        	String url = resource.getUrl();
        	RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
        	Collection<ConfigAttribute> configAtts = Sets.newHashSet(); 
			String resourceIdentifier = resource.getIdentifier();
        	configAtts.add(new SecurityConfig(resourceIdentifier));
        	 this.urlAuthorityMap.put(requestMatcher, configAtts);
        }
        
		List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
		matchers.add(new AntPathRequestMatcher("/login"));
		matchers.add(new AntPathRequestMatcher("/403"));
		matchers.add(new AntPathRequestMatcher("/sessionExpired"));
		ignoreRequestMatcher= new OrRequestMatcher(matchers);
		
    }
}
