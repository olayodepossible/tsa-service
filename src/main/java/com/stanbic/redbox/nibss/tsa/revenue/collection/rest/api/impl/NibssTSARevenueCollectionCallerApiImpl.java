/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.impl;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception.RedBoxTSARevenueCollectionException;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssResponseCode;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.RequestParser;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.ResponseCode;
//import static com.stanbic.redbox.sim.swap.common.util.SimSwapConstant.RESPONSE_DATE_PROP_KEY;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionDateFormatter;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.GenericHTTPResponseObject;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.NibssTSANotificationRequest;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.TSATransactionDetails;
//import com.stanbic.redbox.sim.swap.rest.dto.SimSwapResponse;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.nibss.NibssResponse;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.http.client.CallerClient;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.security.AESCrypter;

import java.io.IOException;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.api.NibssTSARevenueCollectionCallerApi;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionConstant;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.nibss.NibssRequest;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author David
 */
public class NibssTSARevenueCollectionCallerApiImpl
		implements NibssTSARevenueCollectionCallerApi, NibssTSARevenueCollectionConstant {

	private String url;
	private String pingOperation;
	private String loginOperation;
	private String statusOperation;
	private String resetOperation;
	private String notificationOperation;
    private String encryptOperation;
	private String clientCode;
	private String username;
	private String notificationContentType;
	private String encoding;
	private String contentType;
	private String proxyHost;
	private int proxyPort;
	private boolean isOverProxy;

	@Autowired
	private CallerClient client;

	private static final Logger log = LoggerFactory.getLogger(NibssTSARevenueCollectionCallerApiImpl.class);

	@Override
	public void ping(Exchange exchange) throws IOException, RedBoxTSARevenueCollectionException {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside ping operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		exchange.setProperty(OPERATION, "ping");
		String pingURL = url + pingOperation;
		String authorization = exchange.getProperty(NIBSS_AUTHORIZATION, String.class);
		GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
//        Block to remove when connection is in place
//        String pingMock = "{"
//                + "\"responseCode\":\"00\","
//                + "\"responseDescription\":\"SIM SWAP SERVICE RUNNING\""
//                + "}";
//        responseObject.setContentLength(100000);
//        responseObject.setHttpResponseCode("200");
//        responseObject.setHttpResponseMessage(pingMock);
		// Block to remove when connection is in place
//uncomment when connection is in place
		// responseObject = client.sendHTTPGetRequest(pingURL, proxyHost, proxyPort,
		// contentType, encoding, isOverProxy, exchange, authorization);

		//responseObject = client.sendHTTPGetRequest(pingURL, proxyHost, proxyPort, contentType, encoding, isOverProxy,exchange, null);
		 
		
		// log.info("simswap: response from nibss is :\n{}",
		// responseObject.getHttpResponseMessage());
		//String responseCode = responseObject.getHttpResponseCode();
		String responseCode = "200";
		
		exchange.setProperty(PRODUCER_RESPONSE_CODE, responseCode);
		NibssResponse response = (NibssResponse) RequestParser
				.readObjectFromJsonString(responseObject.getHttpResponseMessage(), NibssResponse.class);
		if (responseCode.contains("200") || responseCode.contains("OK")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._00.getDescription());
			exchange.setProperty(RESPONSE_DETAILS, response);
		} else if (responseCode.equalsIgnoreCase("800")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._97.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._97.getDescription());
		} else {
			exchange.setProperty(RESPONSE_CODE, response.getResponseCode());
			// exchange.setProperty(RESPONSE_DESCRIPTION,
			// response.getResponseDescription());
		}

	}

	@Override
	public void reset(Exchange exchange) throws Exception {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside reset operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		exchange.setProperty(OPERATION, "reset");
		String resetURL = url + resetOperation;
		//String authorization = exchange.getProperty(NIBSS_AUTHORIZATION, String.class);
		GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
		NibssRequest request = new NibssRequest();
		request.setUsername(username);
		// request.setSecretKey(exchange.getProperty(SECRET_KEY, String.class));
//        String jsonRequest = RequestParser.writeObjectAsJsonString(request, NibssRequest.class);
		String jsonRequest = RequestParser.writeObjectAsJsonString(request);
//        String resetMock = "{"
//                + "\"status\":\"00\","
//                + "\"message\":\"RESET SUCCESSFUL.USE THE NEW CREDENTIALS SUBSEQUENTLY.\","
//                + "\"otherInfo\":\"sDER!^845045\""
//                + "}";
//        responseObject.setContentLength(100000);
//        responseObject.setHttpResponseCode("200");
//        responseObject.setHttpResponseMessage(resetMock);
		// responseObject = client.sendHTTPRequest(jsonRequest, resetURL, proxyHost,
		// proxyPort, contentType, encoding, isOverProxy, exchange, authorization);
		responseObject = client.sendHTTPRequest(jsonRequest, resetURL, proxyHost, proxyPort, contentType, encoding,
				isOverProxy, exchange, null);
		// log.info("simswap: response from nibss is :\n{}",
		// responseObject.getHttpResponseMessage());
		String responseCode = responseObject.getHttpResponseCode();
		exchange.setProperty(PRODUCER_RESPONSE_CODE, responseCode);
		NibssResponse response = (NibssResponse) RequestParser
				.readObjectFromJsonString(responseObject.getHttpResponseMessage(), NibssResponse.class);
		if (responseCode.contains("200") || responseCode.contains("OK")) {
			exchange.setProperty(RESPONSE_DETAILS, response);
			exchange.setProperty(USER_PWORD, response.getUserPassword());
			exchange.setProperty(USER_TOKEN, response.getUserToken());
		} else if (responseCode.contains("800")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._97.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._97.getDescription());
		} else {
			exchange.setProperty(RESPONSE_CODE, response.getResponseCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, response.getResponseDescription());
		}
	}

	@Override
	public void login(Exchange exchange) throws Exception {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside login operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		exchange.setProperty(OPERATION, "login");
		String loginURL = url + loginOperation;
		String authorization = null;
		GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
		NibssRequest request = new NibssRequest();
		request.setUsername(username);
		request.setUserPassword(exchange.getProperty(USER_PWORD, String.class));
		request.setUserToken(exchange.getProperty(USER_TOKEN, String.class));
		// request.setSecretKey(exchange.getProperty(SECRET_KEY, String.class));
		log.info("USER_PWORD : {}", exchange.getProperty(USER_PWORD, String.class));
		log.info("USER_TOKEN : {}", exchange.getProperty(USER_TOKEN, String.class));
		String jsonRequest = RequestParser.writeObjectAsJsonString(request);
		log.info("Login request is: {}", jsonRequest);
//        String loginMock = "";
//        responseObject.setContentLength(100000);
//        responseObject.setHttpResponseCode("200");
//        responseObject.setHttpResponseMessage(loginMock);
		// responseObject = client.sendHTTPRequest(jsonRequest, loginURL, proxyHost,
		// proxyPort, contentType, encoding, isOverProxy, exchange, authorization);

		responseObject = client.sendHTTPRequest(jsonRequest, loginURL, proxyHost, proxyPort, contentType, encoding,
				isOverProxy, exchange, null);

		// log.info("simswap: response from nibss is :\n{}",
		// responseObject.getHttpResponseMessage());
		String responseCode = responseObject.getHttpResponseCode();
		exchange.setProperty(PRODUCER_RESPONSE_CODE, responseCode);

		if (responseCode.contains("200") || responseCode.contains("OK")) {
//            exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
//            exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._00.getDescription());
//            exchange.setProperty(RESPONSE_DETAILS, response);
              //authorization = exchange.getIn().getHeader("Authorization", String.class);
//            authorization = "CLIENT" + clientCode + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJJTlRFR1JBVE9SIiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDgwLzJGQVNlcnZpY2UvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1MDYwNzI5MzksImV4cCI6MTUwNjE1OTMzOX0.0Nas5e2137dsmw77_Gil7jHfsqr_3Wx4BXTnMpkIb24TGrm1pSL1F5xlZztcYR3qJCwS4sjH41B-9_vlOqopAA";
            //  exchange.setProperty(NIBSS_AUTHORIZATION, authorization);
//            exchange.setProperty(AUTHORIZATION,)
			log.info("NIBSS_AUTHORIZATION : {} " , exchange.getProperty(NIBSS_AUTHORIZATION, String.class));			
			log.info("NibssTSARevenueCollectionConstant.NIBSS_AUTHORIZATION : {} " , exchange.getProperty(NibssTSARevenueCollectionConstant.NIBSS_AUTHORIZATION, String.class));
		      exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
			log.info("Login was successful");
		} else if (responseCode.contains("800")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._97.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._97.getDescription());
		} else {
			NibssResponse response = (NibssResponse) RequestParser
					.readObjectFromJsonString(responseObject.getHttpResponseMessage(), NibssResponse.class);
			exchange.setProperty(RESPONSE_CODE, response.getResponseCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._99.getDescription());
			log.error("Unknown error occured in login operation...");
		}
	}
	
	@Override
	public void encrypt(Exchange exchange) throws Exception {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside encrypt operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		exchange.setProperty(OPERATION, "encrypt");
		String encryptURL = url + encryptOperation;
		String authorization = exchange.getProperty(NIBSS_AUTHORIZATION, String.class);
		GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
		//NibssRequest request = new NibssRequest(); 
		 
		String jsonObjectToEncrypt = exchange.getProperty(TSA_REVENUE_REQUEST_OBJECT_PROP_KEY, String.class); 
		NibssTSANotificationRequest nibssTSANotificationRequest = new NibssTSANotificationRequest(); 
		nibssTSANotificationRequest.setApiUserPassword(exchange.getProperty(USER_PWORD, String.class));
		nibssTSANotificationRequest.setApiUserToken(exchange.getProperty(USER_TOKEN, String.class)); 
		nibssTSANotificationRequest.setToEncrypt(jsonObjectToEncrypt); 
		String unencryptedjsonRequest = RequestParser.writeObjectAsJsonString(nibssTSANotificationRequest);
		log.info("Encryption request is: {}", unencryptedjsonRequest); 
		log.info("authorization token: {}", authorization); 
		 
		responseObject = client.sendHTTPRequest(unencryptedjsonRequest,encryptURL, proxyHost, proxyPort, contentType, encoding,
				isOverProxy, exchange, authorization);
 
		String responseCode = responseObject.getHttpResponseCode();
		//exchange.setProperty(PRODUCER_RESPONSE_CODE, responseCode);
		 
		if (responseCode.contains("200") || responseCode.contains("OK")) {
              exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
//            exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._00.getDescription());
//            exchange.setProperty(RESPONSE_DETAILS, response);
//            authorization = exchange.getIn().getHeader("Authorization", String.class);
//            authorization = "CLIENT" + clientCode + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJJTlRFR1JBVE9SIiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDgwLzJGQVNlcnZpY2UvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1MDYwNzI5MzksImV4cCI6MTUwNjE1OTMzOX0.0Nas5e2137dsmw77_Gil7jHfsqr_3Wx4BXTnMpkIb24TGrm1pSL1F5xlZztcYR3qJCwS4sjH41B-9_vlOqopAA";
//            exchange.setProperty(NIBSS_AUTHORIZATION, authorization);
//            exchange.setProperty(AUTHORIZATION,)
              log.info("Encryped request is: {}", responseObject.getHttpResponseMessage()); 
			exchange.setProperty(ENCRYPTED_REQUEST_OBJECT, responseObject.getHttpResponseMessage());
			log.info("Request Encryption was successful");
		} else if (responseCode.contains("800")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._97.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._97.getDescription());
		} else { 
			exchange.setProperty(RESPONSE_CODE, responseObject.getHttpResponseCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, responseObject.getHttpResponseCodeReasonPhrase());
			log.error("Unknown error occured in encrypt operation...");
		}
	}

	@Override
	public void notification(Exchange exchange) throws Exception {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		log.info("Inside notification operation...");
		log.info("Exchange properties are: \n{}", exchange.getProperties());
		log.info("Exchange headers are:\n{}", exchange.getIn().getHeaders());
		log.info("Exchange body is:\n{}", exchange.getIn().getBody());
		exchange.setProperty(OPERATION, "notification");
		String notificationURL = url + notificationOperation;
		String authorization = exchange.getProperty(NIBSS_AUTHORIZATION, String.class);
		GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
		String userPword = exchange.getProperty(USER_PWORD, String.class);	
		log.info("SecrtKy : {}", userPword);	
		String userToken = exchange.getProperty(USER_TOKEN, String.class);
		log.info("Tokn : {}", userToken);	
		NibssRequest request = new NibssRequest(); 
		NibssResponse response = new NibssResponse();
		//AESCrypter crypter = new AESCrypter(userToken,userPword,null); 
		AESCrypter crypter = new AESCrypter(userPword,userToken); 
		String jsonObjectToEncrypt = exchange.getProperty(TSA_REVENUE_REQUEST_OBJECT_PROP_KEY, String.class);
			
		String unencryptedjsonRequest =  jsonObjectToEncrypt;
		
		//log.info("Json Request To Encrypt : {}", unencryptedjsonRequest);		
		String encryptedjsonRequest = crypter.encrypt(unencryptedjsonRequest);
		
		//String encryptedjsonRequest = crypter.encryptJsonRequestBody(unencryptedjsonRequest);
		
		
		 /*
		 * nibssTSANotificationRequest.setApiUserPassword(userPassword);
		 * nibssTSANotificationRequest.setApiUserToken(userToken);
		 * //nibssTSANotificationRequest.setToEncrypt(encryptedTransactionObject);
		 * nibssTSANotificationRequest.setToEncrypt(jsonObjectToEncrypt);
		 * 
		 * 
		 * 
		 * String jsonRequest =
		 * RequestParser.writeObjectAsJsonString(nibssTSANotificationRequest);
		 * 
		 * log.info("Nibss TSA Notification plain request to nibss:\n{}", jsonRequest);
		 */		
		//String encryptedjsonRequest =   exchange.getProperty(ENCRYPTED_REQUEST_OBJECT, String.class); 
		
		String encryptedjsonRequest1 =   exchange.getProperty(ENCRYPTED_REQUEST_OBJECT, String.class); 
		
		log.info("Nibss TSA Notification encrypted request to nibss:\n{}", encryptedjsonRequest);

		// String encryptedRequest = crypter.encrypt(jsonRequest);

		Date requestDate = NibssTSARevenueCollectionDateFormatter.nibssTSARevenueCollectionDateFormatter(new Date());
		exchange.setProperty(PRODUCER_REQUEST_TIMESTAMP, requestDate);
 
		responseObject = client.sendHTTPRequest(encryptedjsonRequest, notificationURL, proxyHost, proxyPort, notificationContentType,
				encoding, isOverProxy, exchange, authorization);

		Date responseDate = NibssTSARevenueCollectionDateFormatter.nibssTSARevenueCollectionDateFormatter(new Date());
		exchange.setProperty(PRODUCER_REQUEST_TIMESTAMP, responseDate);

		log.info(" Nibss TSA Notification encrypted response Object is :\n{}", responseObject.toString()); 
		String responseCode = responseObject.getHttpResponseCode();
		
		String responseCodePhrase = responseObject.getHttpResponseCodeReasonPhrase();
		log.info("\"Nibss TSA Notification responseCode is :\n{}", responseCode);
		
		log.info("\"Nibss TSA Notification responseCode Phrase is :\n{}", responseCodePhrase);
		exchange.setProperty(PRODUCER_RESPONSE_CODE, responseCode);
		
		exchange.setProperty(PRODUCER_RESPONSE_CODE_PHRASE, responseCodePhrase);

		if (responseCode.contains("200") || responseCode.contains("OK")) { 
			
		//	log.info(" Nibss TSA Notification encrypted response Object getHttpResponseMessage is :\n{}", responseObject.getHttpResponseMessage());
			String responseString = responseObject.getHttpResponseMessage();// crypter.decrypt(responseObject.getHttpResponseMessage());
			// responseString = responseObject.getHttpResponseMessage() ;
			log.info("\"Nibss TSA Notification response String is :\n{}", responseString);

			try {
				response = (NibssResponse) RequestParser.readObjectFromJsonString(responseString, NibssResponse.class);
			} catch (IOException ex) {
				log.info("Error occured...");
				log.info("Could not interpret response from nibss...");
				log.error("Exception is :\n{}", ex.getMessage());
				exchange.setProperty(RESPONSE_CODE, ResponseCode._94.getCode());
				exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._94.getDescription());
			}
			exchange.setProperty(RESPONSE_CODE, ResponseCode._00.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._00.getDescription());
			exchange.setProperty(RESPONSE_DETAILS, response);

		} else if (responseCode.contains("800")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode._97.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._97.getDescription());
		}else if (responseCode.contains("805")) {
			exchange.setProperty(RESPONSE_CODE, ResponseCode.VENDOR_COMMUNICATION_ERROR.getCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode.VENDOR_COMMUNICATION_ERROR.getDescription());
		}else if (responseCode.contains("400")) {
			String responseString = responseObject.getHttpResponseMessage();
			try {
				response = (NibssResponse) RequestParser.readObjectFromJsonString(responseString, NibssResponse.class);
			} catch (IOException ex) {
				log.info("Error occured...");
				log.info("Could not interpret response from nibss...");
				log.error("Exception is :\n{}", ex.getMessage());
				exchange.setProperty(RESPONSE_CODE, ResponseCode._94.getCode());
				exchange.setProperty(RESPONSE_DESCRIPTION, ResponseCode._94.getDescription());
			}
			exchange.setProperty(RESPONSE_CODE, response.getResponseCode());
			exchange.setProperty(RESPONSE_DESCRIPTION, NibssResponseCode.map.get(response.getResponseCode()));
		} else { 
			exchange.setProperty(RESPONSE_CODE, responseCode);
			//exchange.setProperty(RESPONSE_DESCRIPTION, exchange.getProperty(PRODUCER_RESPONSE_CODE_PHRASE , String.class));
			exchange.setProperty(RESPONSE_DESCRIPTION, NibssResponseCode.map.get(response.getResponseCode()));
		}
		log.info("Finished from the notification method...");
	}//

	// @Override
	// public void notification(Exchange exchange) throws Exception { }

	@Override
	public void mdalist(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPingOperation() {
		return pingOperation;
	}

	public void setPingOperation(String pingOperation) {
		this.pingOperation = pingOperation;
	}

	public String getLoginOperation() {
		return loginOperation;
	}

	public void setLoginOperation(String loginOperation) {
		this.loginOperation = loginOperation;
	}

	public String getStatusOperation() {
		return statusOperation;
	}

	public void setStatusOperation(String statusOperation) {
		this.statusOperation = statusOperation;
	}

	public String getResetOperation() {
		return resetOperation;
	}

	public void setResetOperation(String resetOperation) {
		this.resetOperation = resetOperation;
	}

	public CallerClient getClient() {
		return client;
	}

	public void setClient(CallerClient client) {
		this.client = client;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public boolean isIsOverProxy() {
		return isOverProxy;
	}

	public void setIsOverProxy(boolean isOverProxy) {
		this.isOverProxy = isOverProxy;
	}

	public String getNotificationOperation() {
		return notificationOperation;
	}

	public void setNotificationOperation(String notificationOperation) {
		this.notificationOperation = notificationOperation;
	}

	public String getEncryptOperation() {
		return encryptOperation;
	}

	public void setEncryptOperation(String encryptOperation) {
		this.encryptOperation = encryptOperation;
	}

	public String getNotificationContentType() {
		return notificationContentType;
	}

	public void setNotificationContentType(String notificationContentType) {
		this.notificationContentType = notificationContentType;
	}
 
     
	
}
