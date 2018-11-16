package cn.com.cslc.aw.tools.core.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class BaseApplicationException extends BaseException {

	private Model model;
	
	private static final long serialVersionUID = -7787619861956394231L;

	public BaseApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseApplicationException(String message) {
		super(message);
	}

	public BaseApplicationException(Throwable cause) {
		super(cause);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
