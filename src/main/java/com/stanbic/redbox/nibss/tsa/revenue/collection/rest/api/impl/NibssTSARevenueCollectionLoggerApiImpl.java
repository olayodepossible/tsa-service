/**
 * 
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.impl;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionCredentials;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionLog;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.TSARevenueCollectionDetails;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.NibssTSARevenueCollectionLoggerApi;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dao.NibssTSARevenueCollectionLogDAO;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionConstant;

/**
 * @author David
 * 
 */
public class NibssTSARevenueCollectionLoggerApiImpl implements
NibssTSARevenueCollectionLoggerApi {

	@Autowired
	private NibssTSARevenueCollectionLogDAO nibssTSARevenueCollectionLogDAO;
 
	@Override
	public void logNibssTSARevenueCollectionFromExchangeProperty(
			Exchange exchange) throws Exception {
		try {
			NibssTSARevenueCollectionLog nibssTSARevenueCollectionLog = (NibssTSARevenueCollectionLog) exchange
					.getProperty(NibssTSARevenueCollectionConstant.TRANS_LOG_PROP_KEY,
							NibssTSARevenueCollectionLog.class);
			if (nibssTSARevenueCollectionLog != null)
				logNibssTSARevenueCollection(nibssTSARevenueCollectionLog);
		} catch (Exception e) {
			exchange.setProperty(NibssTSARevenueCollectionConstant.IS_BAD_TRANS_STRING_PROP_KEY, "true");
		}
	}

	@Override
	public void logNibssTSARevenueCollection(
			NibssTSARevenueCollectionLog nibssTSARevenueCollectionLog) throws Exception {
		try {
			if (nibssTSARevenueCollectionLog != null) {
				nibssTSARevenueCollectionLogDAO.update(nibssTSARevenueCollectionLog);
			}
		} catch (Exception e) {
			String errorMessage = "<ResponseCode>906</ResponseCode>"
					+ "<ResponseDescription>A database error OCCURRED while processing your request.</ResponseDescription>";
			throw new Exception(errorMessage);
		}
	}

	@Override
	public void logNibssTSARevenueCollectionFromExchangeBody(Exchange exchange)
			throws Exception {
		try {
			NibssTSARevenueCollectionLog nibssTSARevenueCollectionLog = exchange
					.getIn().getBody(NibssTSARevenueCollectionLog.class);
			if (nibssTSARevenueCollectionLog != null)
				logNibssTSARevenueCollection(nibssTSARevenueCollectionLog);
		} catch (Exception e) {
			exchange.setProperty(NibssTSARevenueCollectionConstant.IS_BAD_TRANS_STRING_PROP_KEY, "true");
		}
	}

	
        
	/*
	 * @Override public List<NibssTSARevenueCollectionLog> getSimSwapLog( String
	 * queryString, List<Object> queryParameters, int maxResultCount) { return
	 * nibssTSARevenueCollectionLogDAO.getSimSwapLog( queryString, queryParameters,
	 * maxResultCount); }
	 */
        @Override
        public <T> T find(Class<T> objClass, Object id) {
            return nibssTSARevenueCollectionLogDAO.find(objClass, id);
        }
        @Override
        public void insert(Object log) {
        	nibssTSARevenueCollectionLogDAO.insert(log);
        }
        @Override
        public <T> T update(T log) {
            return nibssTSARevenueCollectionLogDAO.update(log);
        }

		 

		@Override
		public List<NibssTSARevenueCollectionLog> getNibssTSARevenueCollectionLog(String queryString,
				List<Object> queryParameters, int maxResultCount) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<TSARevenueCollectionDetails> getTSARevenueCollectionDetails(String queryString,
				List<Object> queryParameters, int maxResultCount) {
			// TODO Auto-generated method stub
			return nibssTSARevenueCollectionLogDAO.getTSARevenueCollectionDetails(queryString, queryParameters, maxResultCount);
		} 
		
		@Override
		public NibssTSARevenueCollectionCredentials getNibssTSARevenueCollectionCredentialsById(int id)  {
			return nibssTSARevenueCollectionLogDAO.getNibssTSARevenueCollectionCredentialsById(id);
		}

}
