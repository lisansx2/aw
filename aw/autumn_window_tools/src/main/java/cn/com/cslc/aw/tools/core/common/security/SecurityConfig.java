package cn.com.cslc.aw.tools.core.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static String[] DEFAULT_IGNORED_PATH = { "/dist/**","/vendor/**","/ws/**"};

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").successHandler(customLoginSuccessHandler)
		.and()
		.logout().invalidateHttpSession(true)
		.and()
		.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).
		and()
		.sessionManagement().invalidSessionUrl("/sessionExpired")
		.and()
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").anonymous()
		.anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
		for (String pattern : DEFAULT_IGNORED_PATH) {
			matchers.add(new AntPathRequestMatcher(pattern, null));
		}
		web.ignoring().requestMatchers(new OrRequestMatcher(matchers));
	}
}
