package cn.com.cslc.aw.core.user.exception;

import cn.com.cslc.aw.core.common.exception.DataNotFoundException;

public class UsernameNotFoundException extends DataNotFoundException{

	private static final long serialVersionUID = -1182696272086949525L;
	
	public UsernameNotFoundException(String message) {
		super(message);
	}

}
