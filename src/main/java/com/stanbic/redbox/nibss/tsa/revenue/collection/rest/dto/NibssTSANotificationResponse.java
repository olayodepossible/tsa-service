package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "requestId", "batchId","settlementRef","responseCode", "responseDescritpion" })
public class NibssTSANotificationResponse {

	@JsonProperty("requestId")
	private String requestId;
	@JsonProperty("batchId")
	private String batchId;
	@JsonProperty("settlementRef")
	private String settlementRef;
	@JsonProperty("responseCode")
	private String responseCode;
	@JsonProperty("responseDescritpion")
	private String responseDescritpion;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public String getSettlementRef() {
		return settlementRef;
	}

	public void setSettlementRef(String settlementRef) {
		this.settlementRef = settlementRef;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescritpion() {
		return responseDescritpion;
	}

	public void setResponseDescritpion(String responseDescritpion) {
		this.responseDescritpion = responseDescritpion;
	}


	@Override
	public String toString() {
		return "NibssTSANotificationResponse [requestId=" + requestId + ", batchId=" + batchId + ", settlementRef="
				+ settlementRef + ", responseCode=" + responseCode + ", responseDescritpion=" + responseDescritpion
				+ "]";
	}

}
