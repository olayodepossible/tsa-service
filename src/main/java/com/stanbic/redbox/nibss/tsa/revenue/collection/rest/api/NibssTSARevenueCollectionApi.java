package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api;
 


public interface NibssTSARevenueCollectionApi {
	public String getAccountTransactionStatusDetails(  String accountNumber);
	
	public String getAccountTransactionStatusCode(String statusDesc);
}
