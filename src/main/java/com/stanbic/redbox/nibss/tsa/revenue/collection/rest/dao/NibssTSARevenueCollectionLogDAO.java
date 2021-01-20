/**
 * 
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionCredentials;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionLog;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.TSARevenueCollectionDetails; 

/**
 * @author David
 * 
 */
@Transactional("nibssTSARevenueCollectionTransactionManager")
public class NibssTSARevenueCollectionLogDAO {

	private static final Logger LOG = LoggerFactory
			.getLogger(NibssTSARevenueCollectionLogDAO.class);

	//@Autowired
	@PersistenceContext(unitName = "redboxNibssTSARevenueCollectionUnit")
	private EntityManager entityManager;

	

	@Transactional(readOnly = true, propagation = Propagation.NEVER, value = "nibssTSARevenueCollectionTransactionManager")
	public synchronized List<NibssTSARevenueCollectionLog> getNibssTSARevenueCollectionLog(
			String queryString, List<Object> queryParameters, int maxResultCount) {
		List<NibssTSARevenueCollectionLog> nibssTSARevenueCollectionList = new ArrayList<NibssTSARevenueCollectionLog>();
		try {
			Query query = entityManager.createQuery(queryString,
					NibssTSARevenueCollectionLog.class);
			if (queryParameters != null && !queryParameters.isEmpty()) {
				for (int i = 0, j = 1; i < queryParameters.size(); i++, j++) {
					query.setParameter(j, queryParameters.get(i));
				}
			}
			if (maxResultCount > 0)
				query.setMaxResults(maxResultCount);
			nibssTSARevenueCollectionList = query.getResultList();

			// LOG.info("The results is: {}", results.get(0).toString());
		} catch (Exception e) {
			LOG.error("******* Exception OCCURRED in getNibssTSARevenueCollectionLog of NibssTSARevenueCollectionLogDAO:"
					+ e);
			System.out
					.println("******* Exception OCCURRED in getNibssTSARevenueCollectionLog of NibssTSARevenueCollectionLogDAO:"
							+ e.getMessage());
			e.printStackTrace();
		}
		return nibssTSARevenueCollectionList;
	}

	
	@Transactional(readOnly = true, propagation = Propagation.NEVER, value = "nibssTSARevenueCollectionTransactionManager")
	public synchronized List<TSARevenueCollectionDetails> getTSARevenueCollectionDetails(
			String queryString, List<Object> queryParameters, int maxResultCount) {
		List<TSARevenueCollectionDetails> tsaRevenueCollectionDetails = new ArrayList<TSARevenueCollectionDetails>();
		try {
			Query query = entityManager.createQuery(queryString,
					TSARevenueCollectionDetails.class);
			if (queryParameters != null && !queryParameters.isEmpty()) {
				for (int i = 0, j = 1; i < queryParameters.size(); i++, j++) {
					query.setParameter(j, queryParameters.get(i));
				}
			}
			if (maxResultCount > 0)
				query.setMaxResults(maxResultCount);
			tsaRevenueCollectionDetails = query.getResultList();

			// LOG.info("The results is: {}", results.get(0).toString());
		} catch (Exception e) {
			LOG.error("******* Exception OCCURRED in getTSARevenueCollectionDetails of NibssTSARevenueCollectionLogDAO:"
					+ e);
			System.out
					.println("******* Exception OCCURRED in getTSARevenueCollectionDetails of NibssTSARevenueCollectionLogDAO:"
							+ e.getMessage());
			e.printStackTrace();
		}
		return tsaRevenueCollectionDetails;
	}

	
	@Transactional(readOnly = true, propagation = Propagation.NEVER, value = "nibssTSARevenueCollectionTransactionManager")
	public  NibssTSARevenueCollectionCredentials getNibssTSARevenueCollectionCredentialsById(int id) {	
		
		List<NibssTSARevenueCollectionCredentials> credentialsList =  entityManager.createNamedQuery("getNibssTSARevenueCollectionCredentialsById"
				, NibssTSARevenueCollectionCredentials.class)
				.setParameter("id", id)
				.getResultList();
		if(!credentialsList.isEmpty()) {	
		return credentialsList.get(0);
		}else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, value = "nibssTSARevenueCollectionTransactionManager")
	public <T> T find(Class<T> objClass, Object id) {
		return entityManager.find(objClass, id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, value = "nibssTSARevenueCollectionTransactionManager")
	public void insert(Object log) {
		entityManager.persist(log);
		LOG.info("Record entry created successfully");
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, value = "nibssTSARevenueCollectionTransactionManager")
	public <T> T update(T log) {
		T a = null; 
		 a = entityManager.merge(log); 		 
		LOG.info("Record updated successfully");
		return a;
	}
	
	
}
