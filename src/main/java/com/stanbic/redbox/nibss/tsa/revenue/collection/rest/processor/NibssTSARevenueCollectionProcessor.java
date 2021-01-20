package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.processor;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.NibssTSARevenueCollectionLoggerApi;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.NibssTSANotificationRequest;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionConstant;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionCredentials;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.NibssTSARevenueCollectionLog;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.orm.TSARevenueCollectionDetails;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.RequestParser;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.ResponseCode;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionDateFormatter;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.UtilClass;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.NibssTSANotificationResponse;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.TSATransactionDetails;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.nibss.NibssResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

public class NibssTSARevenueCollectionProcessor implements NibssTSARevenueCollectionConstant {

	@Autowired
	private NibssTSARevenueCollectionLoggerApi db;
	private static final Logger log = LoggerFactory.getLogger(NibssTSARevenueCollectionProcessor.class);

	private String bankId;
	private String batchIdCode;
	private String uniqueReferenceCode;
	private String bankBranchid;
	private String cbnAcct;
	private String channel;
	private String customerTin;
	private String feedType;
	private String itemCode;
	private String itemName;
	private String locationCode;
	private String locationName;
	private String fee;
	private String psspCode;
	private String psspName;
	private String requestedAmount;

	public void addInitialProperties(Exchange exchange) {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside addInitialProperties operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		log.info("\n");
		log.info("\n");
		log.info("Trying to get request payload from exchange");
		NibssTSANotificationRequest request = new NibssTSANotificationRequest();
		TSATransactionDetails tsaTransactionDetails = new TSATransactionDetails();
		NibssTSARevenueCollectionCredentials credentials = null;
		exchange.setProperty(IS_ERROR, false);
		// UtilClass util = new UtilClass();
		// exchange.setProperty(LIFETIME_ID, UtilClass.getLifeTimeId(exchange));
		try {
			String requestString = exchange.getIn().getBody(String.class);			
			exchange.setProperty(TSA_REVENUE_REQUEST_OBJECT_PROP_KEY, requestString);
			Date requestDate = NibssTSARevenueCollectionDateFormatter
					.nibssTSARevenueCollectionDateFormatter(new Date());
			exchange.setProperty(REQUEST_DATE_PROP_KEY, requestDate);
			tsaTransactionDetails = (TSATransactionDetails) RequestParser.readObjectFromJsonString(requestString,
					TSATransactionDetails.class);
			List<TSARevenueCollectionDetails> tsaRevenueCollectionDetails = null;
			TSARevenueCollectionDetails collectionDetails = new TSARevenueCollectionDetails();
			if (tsaTransactionDetails != null) {
				log.info("TSA TRANSACTION DETAILS INCOMING OBJECT {}", tsaTransactionDetails.toString());
				String accountNumber = tsaTransactionDetails.getCbnAcct();
				log.info("TS Account Number {}", accountNumber);
				String query = "select A from TSARevenueCollectionDetails A where A.accountNumber ='" + accountNumber
						+ "'";
				log.info("Revenue Collection Details Query {}", query);
				tsaRevenueCollectionDetails = db.getTSARevenueCollectionDetails(query, null, 0);
				log.info("TSA Revenue Collection Details DB {}", tsaRevenueCollectionDetails);
				if (tsaRevenueCollectionDetails != null && tsaRevenueCollectionDetails.size() > 0) {
					log.info("TSA Revenue Collection Details DB {}", tsaRevenueCollectionDetails.toString());
					collectionDetails = tsaRevenueCollectionDetails.get(0);
					log.info("Revenue Collection Details From DB {}", collectionDetails.toString());
					
				//	"branchPhoneno": "",  ----
				//	   "bvn": "",            ----
					tsaTransactionDetails.setBankId(bankId);
					tsaTransactionDetails.setBatchId(UtilClass.generateBatchID(batchIdCode));
					tsaTransactionDetails.setUniqueReference(UtilClass.generateUniqueReferenceId(uniqueReferenceCode));
					// tsaTransactionDetails.setBankBranchid(bankBranchid);
					tsaTransactionDetails.setBankBranchid(collectionDetails.getSortCode());
					// tsaTransactionDetails.setCbnAcct(cbnAcct);
					tsaTransactionDetails.setCbnAcct(accountNumber);
					tsaTransactionDetails.setChannel(channel);
					tsaTransactionDetails.setCustomerTin(customerTin);
					tsaTransactionDetails.setFeedType(feedType);
					tsaTransactionDetails.setItemCode(itemCode);
					tsaTransactionDetails.setItemName(itemName);
					tsaTransactionDetails.setLocationCode(locationCode);
					tsaTransactionDetails.setLocationName(locationName);
					// tsaTransactionDetails.setFee(fee);
					tsaTransactionDetails.setPsspCode(psspCode);
					tsaTransactionDetails.setPsspName(psspName);
					// tsaTransactionDetails.setRequestedAmount(requestedAmount);
					tsaTransactionDetails.setRequestedAmount(tsaTransactionDetails.getRemittedAmount());
					tsaTransactionDetails.setMdaCode(collectionDetails.getMdaCode());
					tsaTransactionDetails.setMdaName(collectionDetails.getMdaName());
					tsaTransactionDetails.setTsaPcCoderef(collectionDetails.getCollectionCode());
					tsaTransactionDetails.setTsaPcCodename(collectionDetails.getCollectionName());
				//	tsaTransactionDetails.setSessionId(UtilClass.getSession());
					tsaTransactionDetails.setSessionId("0");
					tsaTransactionDetails
							.setWhoPays((tsaTransactionDetails.getWhoPays() != null ? tsaTransactionDetails.getWhoPays()
									: "Default"));
					tsaTransactionDetails.setGifmisCode(
							(tsaTransactionDetails.getGifmisCode() != null ? tsaTransactionDetails.getGifmisCode()
									: ""));
					tsaTransactionDetails.setCustomerPhone(
							(tsaTransactionDetails.getCustomerPhone() != null ? tsaTransactionDetails.getCustomerPhone()
									: ""));
					String narration = (tsaTransactionDetails.getNarrationDesc() != null
							? tsaTransactionDetails.getNarrationDesc()
							:"");
					narration = (narration.length() <= 100 ? narration : narration.substring(0, 99));
					tsaTransactionDetails.setSettlementRef("");
					tsaTransactionDetails.setNarrationDesc(narration);
					
					log.info("TRANSACTION DETAILS OBJECT TO MAP : {}", tsaTransactionDetails.toString());

					// String tsaJson = UtilClass.getNotificationJSONString();

					// requestString = RequestParser.writeJsonString(tsaTransactionDetails);
					// log.info("request String to encrypt :\n {}", requestString);

					requestString = UtilClass.getNotificationJSONString(tsaTransactionDetails);
					log.info("request String to encrypt :\n {}", requestString);

					exchange.setProperty(TSA_TRANSACTION_DETAILS_OBJECT, tsaTransactionDetails);
					exchange.setProperty(PRODUCER_REQUEST_PAYLOAD, requestString);

					
					log.info("Successfully got payload from exchange");
					exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
				} else {
					log.info("No Record Found For Account Number Or Invalid Treasury Single Account Supplied");
					exchange.setProperty(RESPONSE_CODE, ResponseCode.INVALID_ACCOUNT_NUMBER.getCode());
					exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode.INVALID_ACCOUNT_NUMBER.getDescription());
					exchange.setProperty(IS_ERROR, true);
				}
			} else {
				log.info("The request payload is null or empty");
				exchange.setProperty(RESPONSE_CODE, ResponseCode.PAYLOAD_PROCESSING_ERROR.getCode());
				exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode.PAYLOAD_PROCESSING_ERROR.getDescription());
				exchange.setProperty(IS_ERROR, true);
			}

		} catch (Exception e) {
			log.info("Error occured while trying to get request payload from exchange with exception \n{}",
					e.getMessage());
			log.info("Incorret payload format");
			exchange.setProperty(RESPONSE_CODE, ResponseCode.PAYLOAD_PROCESSING_ERROR.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode.PAYLOAD_PROCESSING_ERROR.getDescription());
			exchange.setProperty(IS_ERROR, true);
		}

		boolean isError = exchange.getProperty(IS_ERROR, Boolean.class);
		log.info("isError : {}", isError);
		if (isError != true) {
			exchange.setProperty(NO_JWT_IN_DB, false);
			exchange.setProperty(REFRESH_TOKEN, false);
			log.info("Trying to retrieve credentials from db...");

			// credentials = db.getNibssTSARevenueCollectionCredentialsById(1);
			credentials = db.find(NibssTSARevenueCollectionCredentials.class, 1);
			log.info("Credentials retrieved from db...");
			log.info("NibssTSARevenueCollectionCredentials .{}", credentials);

			if (credentials != null && credentials.getAuthorization() != null) {
				log.info("Successfuly got credentials from db.");
				exchange.setProperty(STATUS, credentials.getStatus());
				log.info("Last Token Refresh Date : {}", new Date(credentials.getTokenRefreshDate()));
				if (UtilClass.isTokenValid(credentials.getTokenRefreshDate())) {
					log.info("Token in db is still valid");
					exchange.setProperty(NIBSS_AUTHORIZATION, credentials.getAuthorization());
					exchange.setProperty(USER_PWORD, credentials.getUserPassword());
					exchange.setProperty(USER_TOKEN, credentials.getUserToken());
				} else {
					log.info("Token in db has expired, trying to call nibss to reset token");
					exchange.setProperty(USER_PWORD, credentials.getUserPassword());
					exchange.setProperty(USER_TOKEN, credentials.getUserToken());
					exchange.setProperty(REFRESH_TOKEN, true);
				}
			}

			if (credentials == null || exchange.getProperty(NIBSS_AUTHORIZATION, String.class) == null) {
				log.info("Token not in db, trying to call nibss to set token");
				exchange.setProperty(REFRESH_TOKEN, true);
				exchange.setProperty(NO_JWT_IN_DB, true);
			}
		}
	}

	public void processFinalResponse(Exchange exchange) throws ParseException {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside processFinalResponse operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());

		NibssTSANotificationResponse response = new NibssTSANotificationResponse();
		response.setResponseCode(exchange.getProperty(RESPONSE_CODE, String.class));
		response.setResponseDescritpion(exchange.getProperty(RESPONSE_DESCRIPTION, String.class));

		if (response.getResponseCode() != null && response.getResponseCode().equals(ResponseCode._00.getCode())) {
			NibssResponse nibssResponse = exchange.getProperty(RESPONSE_DETAILS, NibssResponse.class);
			response.setResponseCode(ResponseCode._00.getCode());
			response.setResponseDescritpion(ResponseCode._00.getDescription());
			response.setBatchId(nibssResponse.getBatchId());
			response.setRequestId(nibssResponse.getRequestID());
			response.setSettlementRef(nibssResponse.getSettlementRef());
			
		} else {
			// response.setResponseCode(ResponseCode._94.getCode());
			// response.setResponseDescritpion(ResponseCode._94.getDescription());
			response.setResponseCode(exchange.getProperty(RESPONSE_CODE, String.class));
			response.setResponseDescritpion(exchange.getProperty(RESPONSE_DESCRIPTION, String.class));
			response.setBatchId("");
			response.setRequestId("");
		}

		Date responseDate = NibssTSARevenueCollectionDateFormatter.nibssTSARevenueCollectionDateFormatter(new Date());
		exchange.setProperty(RESPONSE_DATE_PROP_KEY, responseDate);

		String jsonResponse = RequestParser.writeObjectAsJsonString(response);
		exchange.setProperty(TSA_REVENUE_RESPONSE_OBJECT_PROP_KEY, jsonResponse);
		log.info("Final Response :\n{}", jsonResponse);
		exchange.getIn().setBody(jsonResponse, String.class);
	}

	public void processLogCredentials(Exchange exchange) throws Exception {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Executing processLogCredentials...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());

		NibssTSARevenueCollectionCredentials credentials = db.getNibssTSARevenueCollectionCredentialsById(1);
		String operationName = exchange.getProperty(OPERATION, String.class);

		log.info("Logging Credentials for {} operation ", operationName);

		log.info("Credentials Object...{}", credentials);
		if (credentials == null && operationName.contains("reset")) {
			credentials = new NibssTSARevenueCollectionCredentials();
			credentials.setId(1);
			String userPassword = exchange.getProperty(USER_PWORD, String.class);
			String userToken = exchange.getProperty(USER_TOKEN, String.class);
			if (userPassword != null) {
				credentials.setUserPassword(userPassword);
			}
			if (userToken != null) {
				credentials.setUserToken(userToken);
			}
			db.insert(credentials);

			log.info("Credentials Object Logged{}", db.getNibssTSARevenueCollectionCredentialsById(1));

		} else if (credentials != null && operationName.contains("reset")) {
			// credentials.setId(1);
			String userPassword = exchange.getProperty(USER_PWORD, String.class);
			String userToken = exchange.getProperty(USER_TOKEN, String.class);
			if (userPassword != null) {
				credentials.setUserPassword(userPassword);
			}
			if (userToken != null) {
				credentials.setUserToken(userToken);
			}
			db.update(credentials);

		} else if (credentials != null && operationName.contains("login")) {

			String authorization = exchange.getProperty(NibssTSARevenueCollectionConstant.NIBSS_AUTHORIZATION,
					String.class);
			log.info("authorization inside logging : {}", authorization);
			if (authorization != null) {
				credentials.setAuthorization(authorization);
				credentials.setTokenRefreshDate(System.currentTimeMillis());
			}
			log.info("Logging Credentials...{}", credentials.toString());
			db.update(credentials);
			NibssTSARevenueCollectionCredentials updatedCredentials = db.getNibssTSARevenueCollectionCredentialsById(1);

			log.info("updatedCredentials {}", updatedCredentials.toString());
		}
	}

	public void processLogPayload(Exchange exchange) {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		Date requestTime = exchange.getProperty(REQUEST_DATE_PROP_KEY, Date.class);
		Date responseTime = exchange.getProperty(RESPONSE_DATE_PROP_KEY, Date.class);
		String requestPayload = exchange.getProperty(TSA_REVENUE_REQUEST_OBJECT_PROP_KEY, String.class);
		String responsePayload = exchange.getProperty(TSA_REVENUE_RESPONSE_OBJECT_PROP_KEY, String.class);
		String responseCode = exchange.getProperty(RESPONSE_CODE, String.class);
		String responseDescription = exchange.getProperty(RESPONSE_DESCRIPTION, String.class);
		String producerRequestPayload = exchange.getProperty(PRODUCER_REQUEST_PAYLOAD, String.class);
		String producerResponsePayload = exchange.getProperty(PRODUCER_RESPONSE_PAYLOAD, String.class);
		String producerResponseCode = exchange.getProperty(PRODUCER_RESPONSE_CODE, String.class);
		String producerResponseDescription = exchange.getProperty(PRODUCER_RESPONSE_DESCRIPTION, String.class);
		Date producerRequestTime = exchange.getProperty(PRODUCER_REQUEST_TIMESTAMP, Date.class);
		Date producerResponseTime = exchange.getProperty(PRODUCER_RESPONSE_TIMESTAMP, Date.class);

		NibssTSARevenueCollectionLog logObject = new NibssTSARevenueCollectionLog();
		logObject.setProducerRequestPayload(producerRequestPayload);
		logObject.setProducerRequestTime(producerRequestTime);
		logObject.setProducerResponseCode(producerResponseCode);
		logObject.setProducerResponseDescription(producerResponseDescription);
		logObject.setProducerResponsePayload(producerResponsePayload);
		logObject.setProducerResponseTime(producerResponseTime);
		logObject.setRequestPayload(requestPayload);
		logObject.setRequestTime(requestTime);
		logObject.setResponseTime(responseTime);
		logObject.setResponseCode(responseCode);
		logObject.setResponseDescription(responseDescription);
		logObject.setResponsePayload(responsePayload);

		log.info("logObject {}", logObject);
		
		log.info("Finished setting log properties...");
		exchange.setProperty(TRANS_LOG_PROP_KEY, logObject);
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBatchIdCode() {
		return batchIdCode;
	}

	public void setBatchIdCode(String batchIdCode) {
		this.batchIdCode = batchIdCode;
	}

	public String getUniqueReferenceCode() {
		return uniqueReferenceCode;
	}

	public void setUniqueReferenceCode(String uniqueReferenceCode) {
		this.uniqueReferenceCode = uniqueReferenceCode;
	}

	public String getBankBranchid() {
		return bankBranchid;
	}

	public void setBankBranchid(String bankBranchid) {
		this.bankBranchid = bankBranchid;
	}

	public String getCbnAcct() {
		return cbnAcct;
	}

	public void setCbnAcct(String cbnAcct) {
		this.cbnAcct = cbnAcct;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCustomerTin() {
		return customerTin;
	}

	public void setCustomerTin(String customerTin) {
		this.customerTin = customerTin;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPsspCode() {
		return psspCode;
	}

	public void setPsspCode(String psspCode) {
		this.psspCode = psspCode;
	}

	public String getPsspName() {
		return psspName;
	}

	public void setPsspName(String psspName) {
		this.psspName = psspName;
	}

	public String getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(String requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

}
