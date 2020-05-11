package com.pt.library.response;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class Response {

	private boolean isSuccess;
	private String message;
	
	public Response(boolean isSuccess, String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Response [isSuccess=" + isSuccess + ", message=" + message + "]";
	}
}
