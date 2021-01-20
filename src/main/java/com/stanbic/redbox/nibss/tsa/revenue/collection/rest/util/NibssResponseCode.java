package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import java.util.HashMap;
import java.util.Map;

public class NibssResponseCode {
	public static Map<String, String> map;

	static {
		map = new HashMap<>();
		map.put("000", "SUCCESSFUL ");
		map.put("001", "PENDING T24 CREDIT REQUEST");
		map.put("002", "CREDIT REQUEST SENT");
		map.put("500", "INTERNAL SERVER ERROR");
		map.put("800", "JSON MAPPING EXCEPTION");
		map.put("801", "JSON GENERATION EXCEPTION");
		map.put("802", "IO EXCEPTION");
		map.put("803", "INVALID CLIENT EXCEPTION");
		map.put("804", "JWT SIGNATURE EXCEPTION");
		map.put("805", "JWT EXPIRED SIGNATURE EXCEPTION");
		map.put("806", "CLIENTKEY RETRIEVAL ERROR");
		map.put("807", "INVALID EMAIL");
		map.put("808", "INVALID_REQUESTID");
		map.put("809", "NVALID_PHONE");
		map.put("810", "INVALID BANK CODE");
		map.put("811", "NO RECORD FOUND");
		map.put("812", "INVALID TOKEN (JWT)");
		map.put("813", "USED TOKEN");
		map.put("814", "INVALID REQUEST TYPE");
		map.put("815", "INVALID PSSP CODE");
		map.put("816", "INVALID SYSTEM PARAM");
		map.put("817", "INVALID AMOUNT");
		map.put("818", "INVALID MDA CODE");
		map.put("819", "INVALID PAYMENT REFERENCE");
		map.put("820", "INVALID PSSP");
		map.put("821", "INVALID COLLECTION DATE FORMAT");
		map.put("822", "INVALID FEED TYPE");
		map.put("823", "INVALID BULK SIZE ");
		map.put("824", "INVALID BULK REQUEST");
		map.put("825", "NO TRANSACTIONS FOR BATCH");
		map.put("826", "BATCH CLOSED");
		map.put("827", "INVALID BATCHID LENGTH");
		map.put("828", "INVALID CHANNEL CODE");
		map.put("829", "INVALID CREDIT REQUEST DATE FORMAT");
		map.put("830", "CREDIT REQUEST BANK CODE FORMAT");
		map.put("831", "INVALID OR UNKNOWN BATCH");
		map.put("832", "ERROR RETRIEVING BATCH INFO");
		map.put("833", "RETURN EXISTS FOR TRANSACTION");
		map.put("834", "DUPLICATION CREDIT REQUEST");
		map.put("835", "INVALID OR UNKNOW CREDIT REQUEST");
		map.put("836", "INVALID NARRATION");
		map.put("837", "INVALID COLLECTION DETAILS");
		map.put("838", "TOTAL AMOUNT MISMATCH");
		map.put("839", "TOTAL COUNT MISMATCH");
		map.put("840", "INVALID ITEMS COUNT");
		map.put("841", "INVALID BATCH ID FORMAT");
		map.put("842", "INVALID REQUEST LENGTH");
		map.put("843", "INVALID RESPONSE CODE");
		map.put("844", "INVALID COLLECTION CODE");
		map.put("845", "INVALID FEE");
		map.put("846", "DESCRIPTION");
		map.put("847", "INVALID VALUE DATE");
		map.put("848", "INVALID SETTLEMENT REF");
		map.put("849", "INVALID CURRENCY CODE");
		map.put("850", "INVALID CBN ACCOUNT");
		map.put("851", "INVALID CUSTOMER NAME");
		map.put("852", "INVALID MDA NAME");
		map.put("853", "INVALID REMITTED AMOUNT");
		map.put("854", "INVALID TSA PC CODE NAME");
		map.put("855", "INVALID WHO PAYS");
		map.put("907", "EXCEPTION DB ERROR");
		map.put("908", "EXCEPTION");
		map.put("909", "SYSTEM ERROR");
		map.put("910", "FORMAT ERROR");
		map.put("911", "TIME OUT");
		map.put("912", "SECURITY VIOLATION");
		map.put("913", "SECURITY VIOLATION 2");
		map.put("914", "DUPLICATE REQUEST");
		map.put("915", "TRANSACTION NOT PERMITTED TO TERMINAL");
		map.put("916", "TRANSACTION NOT PERMITTED TO USER");
		map.put("917", "INVALID CODE PREFIX");
		map.put("918", "INVALID SUB CODE");
		map.put("919", "INVALID INDUSTRY NAME");
		map.put("920", "INDUSTRY NAME EXISTS");
		map.put("921", "INVALID MERCHANT CODE");
		map.put("922", "Session Window Mismatch");
		map.put("923", "DECRYPTION ERROR");
		map.put("924", "INVALID GRR");
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(map.get("000"));
	}
	
	
	
	

}
