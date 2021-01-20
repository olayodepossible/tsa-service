/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author David
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NibssResponse {
    private String responseCode;
    private String responseDescription;
    private String clientCode;
    @JsonProperty("requestID")
    private String requestID;
    private String telco;
    private String phoneNumber;
    private String simSwapMode;
    private String simSwapDate;
    private String status;
    private String message;
    private String otherInfo;

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

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSimSwapMode() {
        return simSwapMode;
    }

    public void setSimSwapMode(String simSwapMode) {
        this.simSwapMode = simSwapMode;
    }

    public String getSimSwapDate() {
        return simSwapDate;
    }

    public void setSimSwapDate(String simSwapDate) {
        this.simSwapDate = simSwapDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "NibssResponse{" + "responseCode=" + responseCode + ", responseDescription=" + responseDescription + ", clientCode=" + clientCode + ", requestID=" + requestID + ", telco=" + telco + ", phoneNumber=" + phoneNumber + ", simSwapMode=" + simSwapMode + ", simSwapDate=" + simSwapDate + ", status=" + status + ", message=" + message + ", otherInfo=" + otherInfo + '}';
    }
    
    
}
