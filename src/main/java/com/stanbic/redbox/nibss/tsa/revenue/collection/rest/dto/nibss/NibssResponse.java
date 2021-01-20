/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.nibss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author David
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NibssResponse {
	@JsonProperty("responseCode")
    private String responseCode;
	@JsonProperty("responseDesc")
    private String responseDescription;
    private String clientCode;
    @JsonProperty("settlementRef")
    private String settlementRef;
    //@JsonProperty("requestId")//Changed because the returned field is different from what is stated in documentation
    @JsonProperty("paymentReference")
    private String requestID;
    @JsonProperty("batchId")
    private String batchId;

    @JsonProperty("px")
    private String userPassword;
    @JsonProperty("rx")
    private String userToken;  

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    } 
    
    public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
    
	
	/*
	 * public String getTelco() { return telco; }
	 * 
	 * public void setTelco(String telco) { this.telco = telco; }
	 * 
	 * public String getPhoneNumber() { return phoneNumber; }
	 * 
	 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
	 * phoneNumber; }
	 * 
	 * public String getSimSwapMode() { return simSwapMode; }
	 * 
	 * public void setSimSwapMode(String simSwapMode) { this.simSwapMode =
	 * simSwapMode; }
	 * 
	 * public String getSimSwapDate() { return simSwapDate; }
	 * 
	 * public void setSimSwapDate(String simSwapDate) { this.simSwapDate =
	 * simSwapDate; }
	 * 
	 * public String getStatus() { return status; }
	 * 
	 * public void setStatus(String status) { this.status = status; }
	 * 
	 * public String getMessage() { return message; }
	 * 
	 * public void setMessage(String message) { this.message = message; }
	 * 
	 * public String getOtherInfo() { return otherInfo; }
	 * 
	 * public void setOtherInfo(String otherInfo) { this.otherInfo = otherInfo; }
	 */


	public String getSettlementRef() {
		return settlementRef;
	}

	public void setSettlementRef(String settlementRef) {
		this.settlementRef = settlementRef;
	}

	@Override
	public String toString() {
		return "NibssResponse [responseCode=" + responseCode + ", responseDescription=" + responseDescription
				+ ", clientCode=" + clientCode + ", settlementRef=" + settlementRef + ", requestID=" + requestID
				+ ", batchId=" + batchId + ", userPassword=" + userPassword + ", userToken=" + userToken + "]";
	}
    
    
}
