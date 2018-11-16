package cn.com.cslc.aw.core.common.exception;

public class BaseApplicationException extends BaseException {

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

}
