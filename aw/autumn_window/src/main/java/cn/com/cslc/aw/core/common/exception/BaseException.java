package cn.com.cslc.aw.core.common.exception;

public  class BaseException extends RuntimeException {

	private String message;
	
	private Throwable cause;
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	public BaseException(String message) {
		super(message);
		this.message = message;
	}

	public BaseException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
