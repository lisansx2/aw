package cn.com.cslc.aw.core.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import cn.com.cslc.aw.core.common.security.sso.ParseUserIdentifierFromTokenService;
import cn.com.cslc.aw.core.user.service.UserService;

@Configuration
public class AwSecurityConfig extends WebSecurityConfigurerAdapter{

	private static String[] DEFAULT_IGNORED_PATH = { "/dist/**","/vendor/**","/ws/**","/druid/**"};
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ParseUserIdentifierFromTokenService parseUserIdentifierFromTokenService;
	
	@Autowired
	private CustomPreAuthenticationSuccessHandler customPreAuthenticationSuccessHandler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CustomPreAuthenticatedProcessingFilter customPreAuthenticatedProcessingFilter = new CustomPreAuthenticatedProcessingFilter();
		customPreAuthenticatedProcessingFilter.setParseUserIdentifierFromTokenService(parseUserIdentifierFromTokenService);
		customPreAuthenticatedProcessingFilter.setUserService(userService);
		customPreAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManager);
		customPreAuthenticatedProcessingFilter.setAuthenticationSuccessHandler(customPreAuthenticationSuccessHandler);

		FilterSecurityInterceptor customFilterSecurityInterceptor = new FilterSecurityInterceptor();
		customFilterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		customFilterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		customFilterSecurityInterceptor.setObserveOncePerRequest(false);
		customFilterSecurityInterceptor.setSecurityMetadataSource(customInvocationSecurityMetadataSource);
		
		http.addFilter(customPreAuthenticatedProcessingFilter).addFilterAfter(customFilterSecurityInterceptor,FilterSecurityInterceptor.class)
		.formLogin().loginPage("/login").successHandler(customLoginSuccessHandler)
		.and()
		.logout().invalidateHttpSession(true).logoutSuccessHandler(customLogoutSuccessHandler)
		.and()
		.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).
		and()
		.sessionManagement().invalidSessionUrl("/sessionExpired")
		.and()
		.authorizeRequests().antMatchers("/login").anonymous()
		.anyRequest().authenticated();
}

	@Override
	public void configure(WebSecurity web) throws Exception {
		List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
		for (String pattern : DEFAULT_IGNORED_PATH) {
			matchers.add(new AntPathRequestMatcher(pattern, null));
		}
		web.ignoring().requestMatchers(new OrRequestMatcher(matchers));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(
			CustomUserDetailsService userDetailService) {
		PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsByNameServiceWrapper = new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
		userDetailsByNameServiceWrapper.setUserDetailsService(userDetailService);
		preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(userDetailsByNameServiceWrapper);
		return preAuthenticatedAuthenticationProvider;
	}

	@Bean
	public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(roleVoter());
		decisionVoters.add(authenticatedVoter());
		return new AffirmativeBased(decisionVoters);
	}

	@Bean
	public RoleVoter roleVoter() {
		RoleVoter roleVoter = new RoleVoter();
		roleVoter.setRolePrefix("");
		return roleVoter;
	}

	@Bean
	public AuthenticatedVoter authenticatedVoter() {
		return new AuthenticatedVoter();
	}

}
