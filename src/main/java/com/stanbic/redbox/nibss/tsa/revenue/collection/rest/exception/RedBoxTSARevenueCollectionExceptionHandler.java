package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionConstant; 
import java.io.IOException;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


import com.stanbic.redbox.util.RedBoxConstant;
import com.stanbic.redbox.util.exception.RedBoxException;

public class RedBoxTSARevenueCollectionExceptionHandler {

	private static final Logger infoLogger = LoggerFactory
			.getLogger(RedBoxTSARevenueCollectionExceptionHandler.class);

	public void handleTSARevenueCollectionExceptionRequestException(Exchange exchange)
			throws IOException {
		MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
		infoLogger
				.info("In RedBoxTSARevenueCollectionExceptionHandler: handleTSARevenueCollectionExceptionRequestException method...");
		infoLogger.info("Camel Exception caught: "
				+ exchange.getProperty("CamelExceptionCaught", String.class));
		infoLogger.info("RedBox Exception caught: "
				+ exchange.getProperty(RedBoxConstant.CAUGHT_EXCEPTION_KEY,
						String.class));
		infoLogger.debug("Exchange properties: "
				+ exchange.getProperties().toString());
		infoLogger.debug("Exchange headers: "
				+ exchange.getIn().getHeaders().toString());
		infoLogger.debug("Exchange body: "
				+ exchange.getIn().getBody(String.class));

		String redboxCaughtException = exchange.getProperty(
				RedBoxConstant.CAUGHT_EXCEPTION_KEY, String.class);
		Exception exception = redboxCaughtException != null ? exchange
				.getProperty(RedBoxConstant.CAUGHT_EXCEPTION_KEY,
						Exception.class) : exchange.getProperty(
				"CamelExceptionCaught", Exception.class);

		String responseCode = "";
		String responseDescription = "";
		String errorDetails = "";

		if (exception instanceof RedBoxTSARevenueCollectionException) {
			responseCode = ((RedBoxTSARevenueCollectionException) exception)
					.getResponseCode();
			responseDescription = ((RedBoxTSARevenueCollectionException) exception)
					.getResponseDescription();
			errorDetails = ((RedBoxTSARevenueCollectionException) exception)
					.getErrorDetails();
		} else if (exception instanceof RedBoxException) {
			responseCode = ((RedBoxException) exception).getResponseCode();
			responseDescription = ((RedBoxException) exception)
					.getResponseDescription();
		} else if (exception == null) {
			responseCode = ("9XX");
			responseDescription = ("Transaction failure, please contact system admin.");
		} else {
			responseCode = ("9XX");
			responseDescription = exception.getMessage().substring(0, (exception.getMessage().length()>200?200:exception.getMessage().length()));
		}

		exchange.setProperty(NibssTSARevenueCollectionConstant.RESPONSE_CODE,
				responseCode);
		exchange.setProperty(NibssTSARevenueCollectionConstant.RESPONSE_DESCRIPTION,
				responseDescription);
	}
}
