package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto;

public class GenericHTTPRequestObject {
/*
 * > operation: CustomerValidation
> username: testUs3rnam3
> password: testP4ssw0rd
> accountid: 6679
> clientId: 12660
> timestamp: 20180110133212
> hash: Q0UzQzBDOTNCRTkxMUNGQjFGOEUxMjA5NzUwNEU5QTNCRjJEOTM4NEU0QUFGQTBDQjU2REZCMEIyOTFDNTNCOA==
> Content-Length: 14
> Content-Type: application/x-www-form-urlencoded
 * */
	private String operation;
	private String username;
	private String password;
	private String accountid;
	private String clientId;
	private String timestamp;
	private String bet9jaContentType;
	private String contentType;
	private String hash;
	private String secretKey;
	
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getBet9jaContentType() {
		return bet9jaContentType;
	}
	public void setBet9jaContentType(String bet9jaContentType) {
		this.bet9jaContentType = bet9jaContentType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "GenericHTTPRequestObject [operation=" + operation + ", username=" + username + ", password=" + password
				+ ", accountid=" + accountid + ", clientId=" + clientId + ", timestamp=" + timestamp
				+ ", bet9jaContentType=" + bet9jaContentType + ", contentType=" + contentType + ", hash=" + hash
				+ ", secretKey=" + secretKey + "]";
	}
	
	
}
