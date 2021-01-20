package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception;

public class RedBoxTSARevenueCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110062430797287879L;

	private String responseCode;

	private String responseDescription;

	private String errorDetails;

	/**
	 * 
	 */
	public RedBoxTSARevenueCollectionException() {
		super();
		this.errorDetails = this.getMessage();
		this.responseDescription = this.getMessage();
	}

	/**
	 * @param message
	 */
	public RedBoxTSARevenueCollectionException(String message) {
		super(message);
		this.errorDetails = this.getMessage();
		this.responseDescription = this.getMessage();
	}

	/**
	 * @param responseCode
	 * @param responseDescription
	 */
	public RedBoxTSARevenueCollectionException(String responseCode, String responseDescription) {
		super(responseDescription);
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseDescription
	 */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * @param responseDescription
	 *            the responseDescription to set
	 */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	/**
	 * @return the errorDetails
	 */
	public String getErrorDetails() {
		return errorDetails;
	}

	/**
	 * @param errorDetails
	 *            the errorDetails to set
	 */
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RedBoxTransferException [responseCode=" + responseCode
				+ ", responseDescription=" + responseDescription
				+ ", errorDetails=" + errorDetails + "]";
	}
}
