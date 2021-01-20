/**
 *
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api;

import java.util.List;

import org.apache.camel.Exchange;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionCredentials;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionLog;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.TSARevenueCollectionDetails; 

/**
 * @author David
 *
 */
public interface NibssTSARevenueCollectionLoggerApi {

    /**
     * @param exchange
     * @throws Exception
     */
    public void logNibssTSARevenueCollectionFromExchangeProperty(
            Exchange exchange) throws Exception;

    /**
     * @param exchange
     * @throws Exception
     */
    public void logNibssTSARevenueCollectionFromExchangeBody(Exchange exchange)
            throws Exception;

    /**
     * @param creditcardTransaction
     * @throws Exception
     */
    public void logNibssTSARevenueCollection(NibssTSARevenueCollectionLog nibssTSARevenueCollectionLog) throws Exception;

    
   

    /**
     * @param queryString
     * @param queryParameters
     * @param maxResultCount
     * @return 
     */
    public List<NibssTSARevenueCollectionLog> getNibssTSARevenueCollectionLog(
            String queryString, List<Object> queryParameters, int maxResultCount);

    public <T> T find(Class<T> objClass, Object id);
    public void insert(Object log);
    public <T> T update(T log);

	List<TSARevenueCollectionDetails> getTSARevenueCollectionDetails(String queryString, List<Object> queryParameters,
			int maxResultCount);

	NibssTSARevenueCollectionCredentials getNibssTSARevenueCollectionCredentialsById(int id) ;
}
