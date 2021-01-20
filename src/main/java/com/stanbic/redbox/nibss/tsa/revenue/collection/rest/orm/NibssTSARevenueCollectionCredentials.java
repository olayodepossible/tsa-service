/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class NibssTSARevenueCollectionCredentials  implements Serializable{
    
    /**
	 * 
	 */
	
	private static final long serialVersionUID = -4023198571122924165L;
	
	private int id; 
    private String status; 
    private String userToken; 
    private Long tokenRefreshDate; 
    private String userPassword; 
    private String authorization;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Long getTokenRefreshDate() {
		return tokenRefreshDate;
	}
	public void setTokenRefreshDate(Long tokenRefreshDate) {
		this.tokenRefreshDate = tokenRefreshDate;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	@Override
	public String toString() {
		return "NibssTSARevenueCollectionCredentials [id=" + id + ", status=" + status + ", userToken=" + userToken
				+ ", tokenRefreshDate=" + tokenRefreshDate + ", userPassword=" + userPassword + ", authorization="
				+ authorization + "]";
	}
}
