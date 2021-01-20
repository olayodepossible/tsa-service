package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NibssTSARevenueCollectionDateFormatter {

	/**
	 * @param date the string representation "YYYYMMDD" of date to set
	 * @return date
	 * @throws ParseException 
	 */
	public static Date nibssTSARevenueCollectionDateFormatter(Date date) throws ParseException {
//		System.out.println("The date to format is " + date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                String dateFormated = simpleDateFormat.format(date);
		return simpleDateFormat.parse(dateFormated);
	}
	
	public static String finacleDateFormatter(Date date){
		SimpleDateFormat finacleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		return finacleDateFormat.format(date);
	}
}
