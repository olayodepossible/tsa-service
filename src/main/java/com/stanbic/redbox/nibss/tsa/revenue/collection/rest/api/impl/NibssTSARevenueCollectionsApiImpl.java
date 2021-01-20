package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

//import com.stanbic.redbox.nibss.account.transaction.status.rest.dao.NibssAccountTransactionStatusDao;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.NibssTSARevenueCollectionApi;
//import com.stanbic.redbox.nibss.account.transaction.status.rest.orm.AccountTransactionStatusDetails; 

public class NibssTSARevenueCollectionsApiImpl implements  NibssTSARevenueCollectionApi{

	@Override
	public String getAccountTransactionStatusDetails(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccountTransactionStatusCode(String statusDesc) {
		// TODO Auto-generated method stub
		return null;
	}
/**	
	@Autowired
	private NibssAccountTransactionStatusDao nibssAccountTransactionStatusDao;
	AccountTransactionStatusDetails transactionDetails = null;
	private static final Logger logger = LoggerFactory.getLogger(NibssTSARevenueCollectionsApiImpl.class);
	@Override
	public AccountTransactionStatusDetails getAccountTransactionStatusDetails(String accountNumber) {
		
		MDC.put("app.name", "nibss.account.transaction.status");
		logger.info("Inside getAccountTransactionStatusDetails operation...");
		
	  transactionDetails = nibssAccountTransactionStatusDao.getAccountTransactionStatusDetails( accountNumber);
		
		//logger.info("transactionDetails {}" , transactionDetails.toString());
		if(transactionDetails != null) { 
			return transactionDetails;
		} 
		
		logger.info("About to return null{}", transactionDetails);
		return null;
	}

	@Override
	public String getAccountTransactionStatusCode(String statusDesc) {
		// TODO Auto-generated method stub
		return null;
	}
 **/
	
	
	
	
	
}
