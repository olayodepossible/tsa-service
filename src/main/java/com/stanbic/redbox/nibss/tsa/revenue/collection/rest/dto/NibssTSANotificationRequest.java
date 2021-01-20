package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "toencrypt", "px", "rx" })
public class NibssTSANotificationRequest {

	@JsonProperty("toencrypt")
	private String toEncrypt;
	//private TSATransactionDetails toEncrypt;
	@JsonProperty("px")
	private String apiUserPassword;
	@JsonProperty("rx")
	private String apiUserToken;
 

	public String getToEncrypt() {
		return toEncrypt;
	}

	public void setToEncrypt(String toEncrypt) {
		this.toEncrypt = toEncrypt;
	}

	public String getApiUserPassword() {
		return apiUserPassword;
	}

	public void setApiUserPassword(String apiUserPassword) {
		this.apiUserPassword = apiUserPassword;
	}

	public String getApiUserToken() {
		return apiUserToken;
	}

	public void setApiUserToken(String apiUserToken) {
		this.apiUserToken = apiUserToken;
	}

	@Override
	public String toString() {
		return "NibssTSANotificationRequest [toEncrypt=" + toEncrypt + ", apiUserPassword=" + apiUserPassword
				+ ", apiUserToken=" + apiUserToken + "]";
	}

}
