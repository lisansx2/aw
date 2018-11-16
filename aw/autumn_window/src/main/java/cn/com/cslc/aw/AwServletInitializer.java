package cn.com.cslc.aw;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class AwServletInitializer extends SpringBootServletInitializer {
	
	public AwServletInitializer(){
		super();
		setRegisterErrorPageFilter(false);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AwApplication.class);
	}

}
