package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

public class GenericHTTPResponseObject {
/* */
	private String  httpResponseCode;
	private String  httpResponseCodeReasonPhrase;
	private String  httpResponseMessage;
	private boolean isThereHTTPException;
	private String exception;
	private long contentLength;
	//private String  authorization;;
	
	
	public String getHttpResponseCodeReasonPhrase() {
		return httpResponseCodeReasonPhrase;
	}
	public void setHttpResponseCodeReasonPhrase(String httpResponseCodeReasonPhrase) {
		this.httpResponseCodeReasonPhrase = httpResponseCodeReasonPhrase;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public boolean isThereHTTPException() {
		return isThereHTTPException;
	}
	public void setThereHTTPException(boolean isThereHTTPException) {
		this.isThereHTTPException = isThereHTTPException;
	}
	public String getHttpResponseCode() {
		return httpResponseCode;
	}
	public void setHttpResponseCode(String httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}
	public String getHttpResponseMessage() {
		return httpResponseMessage;
	}
	public void setHttpResponseMessage(String httpResponseMessage) {
		this.httpResponseMessage = httpResponseMessage;
	}  

	@Override
	public String toString() {
		return "GenericHTTPResponseObject [httpResponseCode=" + httpResponseCode + ", httpResponseCodeReasonPhrase="
				+ httpResponseCodeReasonPhrase + ", httpResponseMessage=" + httpResponseMessage
				+ ", isThereHTTPException=" + isThereHTTPException + ", exception=" + exception + ", contentLength="
				+ contentLength + "]";
	}
	 
	
	
	
}
