package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.awt.Dimension;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.TSATransactionDetails;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.security.AESCrypter;
import com.stanbic.redbox.util.api.RedBoxLifeTimeIDGeneratorApi;

public class UtilClass implements NibssTSARevenueCollectionConstant {

	private UtilClass() {

	}

	private static final Logger LOG = LoggerFactory.getLogger(UtilClass.class);

	@Autowired
	private RedBoxLifeTimeIDGeneratorApi redBoxLifeTimeIDGeneratorApi;

	private static String applicationName;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String _applicationName) {
		applicationName = _applicationName;
	}
	// public String getHostIP(Exchange exchange){
	// InetAddress ip;
	// String lifeTimeId = getLifeTimeId(exchange);
	// try {
	// ip = InetAddress.getLocalHost();
	// return ip.getHostAddress();
	// } catch (Exception e) {
	// LOG.debug(lifeTimeId + "|Error retrieving IP address processing node...");
	// }
	// return "";
	// }

	public String getLifeTimeId(Exchange exchange) {
		Object lifeTimeId = null;
		try {
//			lifeTimeId = exchange.getIn().getHeader(LIFETIME_ID);
//			if (lifeTimeId == null)
			lifeTimeId = redBoxLifeTimeIDGeneratorApi.getLifeTimeId(exchange);
			exchange.setProperty(LIFETIME_ID, lifeTimeId);
		} catch (Exception e) {
			lifeTimeId = getNowTimeAsNumbers();
			exchange.getIn().setHeader(LIFETIME_ID, lifeTimeId);
			exchange.setProperty(LIFETIME_ID, lifeTimeId);
			LOG.info("Error getting lifeTimeId = " + e.toString() + "but util-generated-another =" + lifeTimeId);
		}
		return lifeTimeId.toString();
	}

	public String getNowYearAndMonthAsISO8601() {
		// Input
		Date date = new Date(System.currentTimeMillis());

		// Conversion
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone("CET"));
		String text = sdf.format(date);
		return text;
	}

	public static String getNowYearAndMonthAsYYMM() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		DateFormat formatter = new SimpleDateFormat("yyMM");// ("EEE, dd/MM/yyyy")
		String tomoros_date = formatter.format(date);
		tomoros_date = formatter.format(date);
		// System.out.println("Today : " + today);
		return tomoros_date;
	}

	public static String getTomorosDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");// ("EEE, dd/MM/yyyy")
		String tomoros_date = formatter.format(date);
		tomoros_date = formatter.format(date);
		// System.out.println("Today : " + today);
		return tomoros_date;
	}

	public static String getDateAsLikeableFormat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date date = cal.getTime();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");// ("EEE, dd/MM/yyyy")
		String tomoros_date = formatter.format(date);
		tomoros_date = formatter.format(date);
		// System.out.println("Today : " + today);
		return tomoros_date;
	}

	public static String getTomorosDate_2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// ("EEE, dd/MM/yyyy")
		String tomoros_date = formatter.format(date);
		tomoros_date = formatter.format(date);
		// System.out.println("Today : " + today);
		return tomoros_date;
	}

	public static String getNowDate() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		// System.out.println("Today : " + today);
		return today;
	}

	// getNowTimeAsNumbers3
	public static String getNowTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		// System.out.println("Today : " + today);
		return today;
	}

	public static String getNowTimeAsNumbers() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		today = today.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		// System.out.println("Today : " + today);:
		return today;
	}

	public static String getNowTimeAsNumbers3() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		System.out.println("Today :yyyy-MM-dd HH:mm:ss = " + today);
		today = today.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		System.out.println("Today : " + today);
		return today;
	}

	public static String getNowTimeAsNumbers3(Date dateStr) {
		Date date = dateStr;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		System.out.println("Today :yyyy-MM-dd HH:mm:ss = " + today);
		today = today.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		System.out.println("Today : " + today);
		return today;
	}

	public static String getNowTimeAsNumbers2() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ssSSS");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		today = today.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		// System.out.println("Today : " + today);:
		return today;
	}

	// 2015-03-30T11:02:06.246
	public static String getNowTimeinFinacleFormat() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// ("EEE, dd/MM/yyyy")
		String today = formatter.format(date);
		today = formatter.format(date);
		today = today.substring(0, 10) + "T" + today.substring(10, today.length()).trim();
		System.out.println("Today in finacle format : " + today);
		return today;
	}

	public static String formatTime(String datein) {

		String pattern = "yyyy-MM-dd HH:mm:ss";
		// SimpleDateFormat format = new SimpleDateFormat(pattern);
		String date = new Date().toString();
		DateFormat dfm = new SimpleDateFormat(pattern);

		try {

			// System.out.println("original datein: " + datein);
			Date a = dfm.parse(datein);

			// System.out.println("datein a : " + a);
			// Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = dfm.format(a);
			// System.out.println("dateout f " + date);

		} catch (Exception e) {

			try {
				String s = getNowTime();
				// System.out.println("dateout f " + s);
				return s;
			} catch (Exception ee) {
				// ee.printStackTrace();
				// System.out.println("dateout f " + date);
				// return date;
			}
		}

		// Date date2 = new Date(datein);
		// Date date = Calendar.getInstance().getTime();
		// DateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//("EEE, dd/MM/yyyy")
		// String today = formatter.format(date2);
		// today = formatter.format(date);

		if (date != null) {
			return date;// .toString();
		} else {
			return null;
		}
	}

	public String getAMorPM() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("a");// ("EEE, dd/MM/yyyy")
		String am_pm = formatter.format(date);
		am_pm = formatter.format(date);

		// System.out.println("AM or PM : " + am_pm);
		return am_pm;
	}

	public static void main(String[] a) {

		try {
			String timestamp = UtilClass.getNowTimeAsNumbers3();
			// System.out.println("timestamp : " + timestamp);
			// System.out.println("getNowTimeAsNumbers : " + new UtilClass().getNowHour());

			boolean isNotTrue = false ;
			
			//if(isNotTrue != false) {
				System.out.println("isNotTrue : " + !isNotTrue);
			//}
			
			
			String json = "{\"toencrypt\":\"{\\\"uniqueReference\\\": \\\"STA20200707085258889\\\",\\\"bankBranchid\\\": \\\"221\\\",\\\"bankId\\\": \\\"221150014\\\",\\\"batchId\\\":\\\"STA22120200707222510\\\",\\\"cbnAcct\\\":\\\"\\\",\\\"channel\\\":\\\"05\\\",\\\"collectedAmount\\\":\\\"3157.5\\\",\\\"currency\\\":\\\"NGN\\\",\\\"customerAccount\\\":\\\"\\\",\\\"customerEmail\\\":\\\"ranmad@gmail.com\\\",\\\"customerName\\\":\\\"Rancell Ahmad\\\",\\\"customerPhone\\\":\\\"\\\",\\\"customerTin\\\":\\\"\\\",\\\"feedType\\\":\\\"C\\\",\\\"gifmisCode\\\":\\\"\\\",\\\"itemCode\\\":\\\"\\\",\\\"itemName\\\":\\\"\\\",\\\"locationCode\\\":\\\"2345\\\",\\\"locationName\\\":\\\"Victoria Island\\\",\\\"mdaCode\\\":\\\"011102400100\\\",\\\"narrationDesc\\\":\\\"Rancellteller#amt:3157.5MDAFEDERALROADSAFETYCO\\\",\\\"payColDate\\\":\\\"2019-09-22-11-20-12\\\",\\\"psspCode\\\":\\\"STA\\\",\\\"psspName\\\":\\\"STANBICIBTC\\\",\\\"remittedAmount\\\":\\\"3000\\\",\\\"requestedAmount\\\":\\\"\\\",\\\"sessionId\\\":\\\"1\\\",\\\"settlementRef\\\":\\\"3356767453\\\",\\\"tsaPcCoderef\\\":\\\"480664891\\\",\\\"valueDate\\\":\\\"22-09-2019\\\"}\",\"px\":\"T?YSG@?oZo~3ew7@\",\"rx\":\")*Xzd~n@SHbxWA$L\"}";

			System.out.println("JSON OBJECT : " + json.replace("\\", "").replace(" ", ""));

			System.out.println("isTokenValid : " + isTokenValid(1593509471281l));
			System.out.println("System.currentTimeMillis() : " + System.currentTimeMillis());

			System.out.println("Session Now : " + getSession());
			// java.sql.Timestamp.valueOf(
			Timestamp timeSt = Timestamp.valueOf("2019-08-09 17:31:00.883389");
			System.out.println("timeSt " + timeSt);

			System.out.println("formatTime : " + formatTime("2019-08-09 17:31:00"));

			// System.out.println("generateSecRandomNumber() " + generateSecRandomNumber());

			System.out.println("Batch ID " + generateBatchID("STA221"));
			System.out.println("Unique Reference ID " + generateUniqueReferenceId("STA05221"));
			getDateDiff();

			// padSecRandom("STA221");
			System.out.println("padSecRandom(\"STA221\")" + padSecRandom("STA2234667"));
			System.out.println("getNowHour()" + getNowHour());
			
			//String userPword = "Z$aVXMB~@DN876@(";
			///String userToken = "M&aW1NHf9s30Fial";
			
			 //String iv = "M&aW1NHf9s30Fial"; //
			 //String secretkey = "Z$aVXMB~@DN876@(";//Z$aVXMB~@DN876@(
			 String iv = "MvO*~Ybk(izFicJ#"; //
			 String secretkey = "ja!(qAr!Q44g%g@3";//Z$aVXMB~@DN876@(
			 
			 //"px":"ja!(qAr!Q44g%g@3","rx":"MvO*~Ybk(izFicJ#"
			 
			 //"px":"pfzt9Gb^ZtT(RY1E","rx":"7)oqAnn^xAS&IXBV"
			 
				String userPword = "pfzt9Gb^ZtT(RY1E";
				String userToken = "7)oqAnn^xAS&IXBV";
			
			AESCrypter crypter = new AESCrypter(userPword,userToken);			
			String notificationJson = getNotificationJSONString(setTSATransactionDetails());			
			System.out.println("notificationJson : " + notificationJson);
			System.out.println("Encrypted Notification Json : " + crypter.encryptJsonRequestBody(notificationJson));
			
			System.out.println("Decrypted Notification Json : " + crypter.decrypt(crypter.encryptJsonRequestBody(notificationJson)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("int hour =" + Integer.parseInt(UtilClass.getNowHour()));
		// System.out.println("int year =" + Integer.parseInt(UtilClass.getNowYear()));
		// System.out.println("int month =" +
		// Integer.parseInt(UtilClass.getNowMonth()));
		// System.out.println("int day =" + (Integer.parseInt(UtilClass.getNowDay()) +1)
		// );
		// System.out.println("int minute =" +
		// (Integer.parseInt(UtilClass.getNowMinute())) );
		// System.out.println("int second =" +
		// (Integer.parseInt(UtilClass.getNowSecond()) +1) );
	}

	public static TSATransactionDetails setTSATransactionDetails() {
	    TSATransactionDetails tsaTransactionDetails = new TSATransactionDetails();
		tsaTransactionDetails.setUniqueReference(generateUniqueReferenceId("STA05221"));
		tsaTransactionDetails.setBankBranchid("221999999");
		tsaTransactionDetails.setBankId("221");
		tsaTransactionDetails.setBatchId(generateBatchID("STA221"));
		tsaTransactionDetails.setCbnAcct("0028938070");
		tsaTransactionDetails.setChannel("05");
		tsaTransactionDetails.setCollectedAmount("98977.36");
		tsaTransactionDetails.setCurrency("NGN");
		tsaTransactionDetails.setCustomerEmail("");
		tsaTransactionDetails.setCustomerName("Unknown");
		tsaTransactionDetails.setCustomerPhone("");
		tsaTransactionDetails.setCustomerTin("00000");
		tsaTransactionDetails.setFee("0.01");
		tsaTransactionDetails.setGifmisCode("");
		tsaTransactionDetails.setItemCode("1234567");
		tsaTransactionDetails.setItemName("Payment");
		tsaTransactionDetails.setLocationCode("2345");
		tsaTransactionDetails.setLocationName("Victoria Island");
		tsaTransactionDetails.setMdaCode("00000044");
		tsaTransactionDetails.setMdaName("NESS");
		tsaTransactionDetails.setNarrationDesc("TEST");
		tsaTransactionDetails.setPayColDate("2020-08-24-10-30-11");
		tsaTransactionDetails.setPsspCode("STA");
		tsaTransactionDetails.setPsspName("STANBICIBTC");
		tsaTransactionDetails.setRemittedAmount("98977.36");
		tsaTransactionDetails.setRequestedAmount("98977.36");
		tsaTransactionDetails.setSessionId(getSession());
		tsaTransactionDetails.setSettlementRef("S5095650701");
		tsaTransactionDetails.setTsaPcCodename("TSA-PAYMENT");
		tsaTransactionDetails.setTsaPcCoderef("480664891");
		tsaTransactionDetails.setValueDate("24-08-2020");
		tsaTransactionDetails.setWhoPays("Default");		
		return tsaTransactionDetails;
	}
	
	public static String getSession() {
		int nowHour = Integer.parseInt(getNowHour());
		String morningSession = "0";
		System.out.println("nowHour " + nowHour);
		if (nowHour >= 11 && nowHour < 17) {
			return morningSession;
		} else {
			return "1";
		}
	}

	public static void getDateDiff() {
		// 1595158986512
		Date d2 = new Date();
		Date d1 = new Date(1595158986512l);

		System.out.println("d1.getTime() " + d1.getTime());
		System.out.println("d2.getTime() " + d2.getTime());

		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);
		int diffInDays = (int) diff / (1000 * 60 * 60 * 24);

		System.out.println(diffInDays + "  days");
		System.out.println(diffHours + "  Hour");
		System.out.println(diffMinutes + "  min");
		System.out.println(diffSeconds + "  sec");
	}

	public static boolean isTokenValid(Long tokenRefreshTime) {
		Date d2 = new Date();
		Date d1 = new Date(tokenRefreshTime);
		long diff = d2.getTime() - d1.getTime();
		long diffHours = diff / (60 * 60 * 1000);

		if (diffHours <= 24) {
			return true;
		}
		return false;
	}

	public static String generateDateTimeValue() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date(System.currentTimeMillis());
		return simpleDateFormat.format(date);
	}

	public static Date getTomorrowsDate() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return dt;
	}

	public static String getNowMinute() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("mm");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		System.out.println("minute : " + hour);
		return hour;
	}

	public static String getNowSecond() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("ss");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		System.out.println("second : " + hour);
		return hour;
	}

	public static String getNowHour() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("k");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		// System.out.println("Hour : " + hour);
		return hour;
	}

	public static String getNowMonth() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("MM");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		// System.out.println("Month : " + hour);
		return hour;
	}

	public static String getNowDay() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		System.out.println("day : " + hour);
		return hour;
	}

	public static String getNowYear() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy");// ("EEE, dd/MM/yyyy")
		String hour = formatter.format(date);
		hour = formatter.format(date);

		System.out.println("Year : " + hour);
		return hour;
	}

	public static String getODDorEVEN() {
		String divby2 = "odd";
		String hour = getNowHour();
		int h = Integer.parseInt(hour);

		if (h % 2 == 0) {
			divby2 = "even";
		}

		// System.out.println("Hour : " + hour);
		// System.out.println("getODDorEVEN : " + divby2);
		return divby2;
	}

	public static String getTodaysDate() {
		return getNowTime().substring(0, 10);
	}

	public static String getDayOfTheWeek() {

		String nowtime = getNowTime();
		int year = Integer.parseInt(nowtime.substring(0, 4));
		int month = Integer.parseInt(nowtime.substring(5, 7));
		int date = Integer.parseInt(nowtime.substring(8, 10));

		// System.out.println(year);
		// System.out.println(month);
		// System.out.println(date);

		Calendar calendar = new GregorianCalendar();

		// calendar.set(year, month-1, date); // 0 = January
		calendar.set(year, --month, date); // 0 = January

		// System.out.println("today is " + calendar.get(Calendar.DAY_OF_WEEK));
		// int i = Calendar.DAY_OF_WEEK ;
		// System.out.println( "Calendar.DAY_OF_WEEK = " +
		// calendar.DAY_OF_WEEK);

		/*
		 * Monday Tuesday 3 Wednesday Thursday Friday Saturday 6 Sunday 7
		 */
		month--;

		// GregorianCalendar cal = new GregorianCalendar(year, month, date);
		int i = calendar.get(Calendar.DAY_OF_WEEK);

		String dayOfTheWeek = "";

		if (i == 2) {
			dayOfTheWeek = "Monday";
		} else if (i == 3) {
			dayOfTheWeek = "Tuesday";
		} else if (i == 4) {
			dayOfTheWeek = "Wednesday";
		} else if (i == 5) {
			dayOfTheWeek = "Thursday";
		} else if (i == 6) {
			dayOfTheWeek = "Friday";
		} else if (i == 7) {
			dayOfTheWeek = "Saturday";
		} else if (i == 1) {
			dayOfTheWeek = "Sunday";
		}

		// System.out.println(dayOfTheWeek);
		return dayOfTheWeek;
	}

	/*
	 * Calendar calendar = new GregorianCalendar(); calendar.set(2011, 0, 1); // 0 =
	 * January System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
	 */
	public static int getDayNumberOfTheWeek() {
		String nowtime = getNowTime();

		int year = Integer.parseInt(nowtime.substring(0, 4));
		int month = Integer.parseInt(nowtime.substring(5, 7));
		int date = Integer.parseInt(nowtime.substring(8, 10));

		Calendar calendar = new GregorianCalendar();
		calendar.set(year, --month, date);
		month--;

		int i = calendar.get(Calendar.DAY_OF_WEEK);
		return i;
	}

	private static String modify(String m) {
		if (m.length() == 1)
			return ("0" + m);
		else
			return (m);
	}

	public static String MobileNumberToFinacleFormat(String intFormat) {
		String prefix = "+";
		String suffix = "";

		prefix += intFormat.substring(0, 3);
		suffix = intFormat.substring(3);

		return prefix + "(0)" + suffix;
	}

	public static String formatXML(String originalXML) {
		return originalXML.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("><", ">\n<").replaceAll("xmlns",
				"\nxmlns");
	}

	public static void formatXML(Exchange exchange) {
		String payload = exchange.getIn().getBody(String.class);

		payload = formatXML(payload);

		exchange.getIn().setBody(payload);
	}

	public static Double round2dp(Double input) {
		double roundOff = Math.round(input * 100) / 100.00;
		return roundOff;
	}

	/* Converts NUBAN Number 1234567890 to 123XXX7890 */
	public static String maskNUBANNumber(String NUBAN) {
		String maskedNUBAN = "";

		try {
			maskedNUBAN = NUBAN.substring(0, 3) + "XXX" + NUBAN.substring(6);
		}

		catch (Exception e) {
			System.out.println("\n\n*** Error Masking NUBAN - " + NUBAN + " - " + e.getMessage());
			e.printStackTrace();

			MDC.put("app.name", applicationName);
			LOG.info("\n\n*** Error Masking NUBAN - " + NUBAN + " - " + e.getMessage());
		}

		return maskedNUBAN;
	}

	// public static String randomNumber(int length) {
	//
	// final String chars = "0123456789";
	// // final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//
	// Random rand = new Random();
	//
	// StringBuilder buf = new StringBuilder();
	//
	// for (int i = 0; i < length; i++) {
	// buf.append(chars.charAt(rand.nextInt(chars.length())));
	// }
	//
	// return buf.toString();
	// }

	/*
	 * This function adds Nigeria CountryCode (CC) Prefix (234) to numbers that are
	 * in the local format these numbers are identified as being - 11 digits in
	 * length - starting with '0'
	 * 
	 * otherwise, the original argument is returned
	 */
	public static String padCountryCodePrefix(String phoneNo) {

		final String CountryCode_Prefix = "234";
		// final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if (phoneNo.length() == 11 && phoneNo.startsWith("0")) {
			phoneNo = CountryCode_Prefix + phoneNo.substring(1);
		} else if (phoneNo.length() == 13 && phoneNo.startsWith("234")) {
		}

		return phoneNo;
	}

	public static String getSimpleMSISDN(String input) {
		Long longValue = null;
		String simpleMSISDN = "";

		try {
			longValue = Long.valueOf(padCountryCodePrefix(input));
		}

		catch (Exception e) {
			return simpleMSISDN;
		}

		simpleMSISDN = String.valueOf(longValue);

		if (simpleMSISDN.startsWith("+"))
			simpleMSISDN = simpleMSISDN.substring(1);

		return simpleMSISDN;
	}

	private static Long dayToMiliseconds(int days) {
		Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
		return result;
	}

	public static Timestamp addDays(Timestamp t1, int days) {
		Long miliseconds = dayToMiliseconds(days);
		return new Timestamp(t1.getTime() + miliseconds);
	}

	// public static String randomString(int length) {
	//
	// final String chars = "0123456789";
	// // final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//
	// Random rand = new Random();
	//
	// StringBuilder buf = new StringBuilder();
	//
	// for (int i = 0; i < length; i++) {
	// buf.append(chars.charAt(rand.nextInt(chars.length())));
	// }
	//
	// return buf.toString();
	// }

	// public static String generateRandomNumberAsString() {
	// String uniqueNumber = "";
	// uniqueNumber = (new Double(Math.random()).toString());
	// return uniqueNumber;
	// }
	public static java.sql.Date getYesterdaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		java.util.Date utilState = cal.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilState.getTime());
		// System.out.println( cal.getTime().getClass().getName().toString());
		// System.out.println( sqlDate.getClass().getName().toString());
		// System.out.println(sqlDate);
		return sqlDate;
	}

	public static java.sql.Date getTodaysDateAsSQLDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		java.util.Date utilState = cal.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilState.getTime());
		// System.out.println( cal.getTime().getClass().getName().toString());
		// System.out.println( sqlDate.getClass().getName().toString());
		// System.out.println(sqlDate);
		return sqlDate;
	}

	public static String getYesterdayDateString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime()).toString();
	}

	public static String getThousandSeperatedNumber(String arg) {
		int numberToConvert = Integer.parseInt(arg);

		return getThousandSeperatedNumber(numberToConvert);
	}

	public static String getThousandSeperatedNumber(int arg) {
		String thousandSeparatedAmount = NumberFormat.getNumberInstance(Locale.UK).format(arg);

		return thousandSeparatedAmount;
	}

	public static String generateSecRandomNumber() {
		SecureRandom ranGen = new SecureRandom();
		// ranGen.setSeed((new Date()).getTime());
		int generatedRand = ranGen.nextInt(10000000);

		// return (Integer.toString(generatedRand).length() == 7?generatedRand:
		// generatedRand);
		System.out.println("generatedRand : " + generatedRand);
		return Integer.toString(generatedRand);
	}

	public static String generateBatchID(String psspCode) {
		String secRandom = generateSecRandomNumber();
		System.out.println("secRandom : " + secRandom);
		String requestId = psspCode + generateDateTimeValue().substring(0, 7) + padSecRandom(secRandom);
		// + (secRandom.length() == 7 ? secRandom : secRandom.concat("0"));
		return requestId;
	}

	public static String padSecRandom(String secRandom) {
		System.out.println("padSecRandom : " + secRandom);
		String s = "";
		char c;
		if (secRandom.length() == 7) {
			return secRandom;
		} else if (secRandom.length() > 7) {
			return secRandom.substring(0, 7);
		} else {
			while (secRandom.length() < 7) {
				System.out.println("padSecRandom length is less than 7 : " + secRandom.length());
				secRandom += "0";
				System.out.println("padSecRandom : " + secRandom);
			}
		}
		System.out.println(secRandom + "");
		return secRandom;
	}

	public static String generateUniqueReferenceId(String seeds) {
		// psspcode+channelcode+bankId+12 random numbers
		// STA05221
		String secRandom = generateSecRandomNumber();
		System.out.println("secRandom : " + secRandom);
		String requestId = seeds + generateDateTimeValue().substring(5, 10) + padSecRandom(secRandom);
		// + (secRandom.length() == 7 ? secRandom : secRandom.concat("0"));
		return requestId;
	}

	public String getNotificationJSONString(String uniqueReference, String bankBranchid, String bankId, String batchId,
			String branchPhoneno, String bvn, String cbnAcct, String channel, String collectedAmount, String currency,
			String customerAccount, String customerEmail, String customerName, String customerPhone, String customerTin,
			String fee, String feedType, String itemCode, String itemName, String locationCode, String locationName,
			String mdaCode, String mdaName, String narrationDesc, String payColDate, String psspCode, String psspName,
			String remittedAmount, String requestedAmount, String sessionId, String gifmisCode, String tsaPcCoderef,
			String tsaPcCodename, String valueDate, String whoPays) {
		String placeHolder = "{" + "\"uniqueReference\":\"" + uniqueReference + "\",\"bankBranchid\":\"" + bankBranchid
				+ "\",\"bankId\":\"" + bankId + "\",\"batchId\":\"" + batchId + "\",\"branchPhoneno\":\""
				+ branchPhoneno + "\",\"bvn\":\"" + bvn + "\",\"cbnAcct\":\"" + cbnAcct + "\",\"channel\":\"" + channel
				+ "\",\"collectedAmount\":\"" + collectedAmount + "\",\"currency\":\"" + currency
				+ "\",\"customerAccount\":\"" + customerAccount + "\",\"customerEmail\":\"" + customerEmail
				+ "\",\"customerName\":\"" + customerName + "\",\"customerPhone\":\"" + customerPhone
				+ "\",\"customerTin\":\"" + customerTin + "\",\"fee\":\"" + fee + "\",\"feedType\":\"" + feedType
				+ "\",\"itemCode\":\"" + itemCode + "\",\"itemName\":\"" + itemName + "\",\"locationCode\":\""
				+ locationCode + "\",\"locationName\":\"" + locationName + "\",\"mdaCode\":\"" + mdaCode
				+ "\",\"mdaName\":\"" + mdaName + "\",\"narrationDesc\":\"" + narrationDesc + "\",\"payColDate\":\""
				+ payColDate + "\",\"psspCode\":\"" + psspCode + "\",\"psspName\":\"" + psspName
				+ "\",\"remittedAmount\":\"" + remittedAmount + "\",\"requestedAmount\":\"" + requestedAmount
				+ "\",\"sessionId\":\"" + sessionId + "\",\"gifmisCode\":\"" + gifmisCode + "\",\"tsaPcCoderef\":\""
				+ tsaPcCoderef + "\",\"tsaPcCodename\":\"" + tsaPcCodename + "\",\"valueDate\":\"" + valueDate
				+ "\",\"whoPays\":\"" + whoPays + "\"}";
		return placeHolder;
	}

	public static String getNotificationJSONString(TSATransactionDetails tsaTransactionDetails) {
		if (tsaTransactionDetails == null) {
			return null;
		}
		String placeHolder = "{" + "\"uniqueReference\":\""
				+ (tsaTransactionDetails.getUniqueReference() != null ? tsaTransactionDetails.getUniqueReference() : "")
				+ "\",\"bankBranchid\":\""
				+ (tsaTransactionDetails.getBankBranchid() != null ? tsaTransactionDetails.getBankBranchid() : "")
				+ "\",\"bankId\":\""
				+ (tsaTransactionDetails.getBankId() != null ? tsaTransactionDetails.getBankId() : "")
				+ "\",\"batchId\":\""
				+ (tsaTransactionDetails.getBatchId() != null ? tsaTransactionDetails.getBatchId() : "")
				+ "\",\"branchPhoneno\":\""
				+ (tsaTransactionDetails.getBranchPhoneno() != null ? tsaTransactionDetails.getBranchPhoneno() : "")
				+ "\",\"bvn\":\"" + (tsaTransactionDetails.getBvn() != null ? tsaTransactionDetails.getBvn() : "")
				+ "\",\"cbnAcct\":\""
				+ (tsaTransactionDetails.getCbnAcct() != null ? tsaTransactionDetails.getCbnAcct() : "")
				+ "\",\"channel\":\""
				+ (tsaTransactionDetails.getChannel() != null ? tsaTransactionDetails.getChannel() : "")
				+ "\",\"collectedAmount\":\""
				+ (tsaTransactionDetails.getCollectedAmount() != null ? tsaTransactionDetails.getCollectedAmount() : "")
				+ "\",\"currency\":\""
				+ (tsaTransactionDetails.getCurrency() != null ? tsaTransactionDetails.getCurrency() : "")
				+ "\",\"customerAccount\":\""
				+ (tsaTransactionDetails.getCustomerAccount() != null ? tsaTransactionDetails.getCustomerAccount() : "")
				+ "\",\"customerEmail\":\""
				+ (tsaTransactionDetails.getCustomerEmail() != null ? tsaTransactionDetails.getCustomerEmail() : "")
				+ "\",\"customerName\":\""
				+ (tsaTransactionDetails.getCustomerName() != null ? tsaTransactionDetails.getCustomerName() : "")
				+ "\",\"customerPhone\":\""
				+ (tsaTransactionDetails.getCustomerPhone() != null ? tsaTransactionDetails.getCustomerPhone() : "")
				+ "\",\"customerTin\":\""
				+ (tsaTransactionDetails.getCustomerTin() != null ? tsaTransactionDetails.getCustomerTin() : "")
				+ "\",\"fee\":\"" + (tsaTransactionDetails.getFee() != null ? tsaTransactionDetails.getFee() : "")
				+ "\",\"feedType\":\""
				+ (tsaTransactionDetails.getFeedType() != null ? tsaTransactionDetails.getFeedType() : "")
				+ "\",\"itemCode\":\""
				+ (tsaTransactionDetails.getItemCode() != null ? tsaTransactionDetails.getItemCode() : "")
				+ "\",\"itemName\":\""
				+ (tsaTransactionDetails.getItemName() != null ? tsaTransactionDetails.getItemName() : "")
				+ "\",\"locationCode\":\""
				+ (tsaTransactionDetails.getLocationCode() != null ? tsaTransactionDetails.getLocationCode() : "")
				+ "\",\"locationName\":\""
				+ (tsaTransactionDetails.getLocationName() != null ? tsaTransactionDetails.getLocationName().trim() : "")
				+ "\",\"mdaCode\":\""
				+ (tsaTransactionDetails.getMdaCode() != null ? tsaTransactionDetails.getMdaCode() : "")
				+ "\",\"mdaName\":\""
				+ (tsaTransactionDetails.getMdaName() != null ? tsaTransactionDetails.getMdaName() : "")
				+ "\",\"narrationDesc\":\""
				+ (tsaTransactionDetails.getNarrationDesc() != null ? tsaTransactionDetails.getNarrationDesc() : "")
				+ "\",\"payColDate\":\""
				+ (tsaTransactionDetails.getPayColDate() != null ? tsaTransactionDetails.getPayColDate() : "")
				+ "\",\"psspCode\":\""
				+ (tsaTransactionDetails.getPsspCode() != null ? tsaTransactionDetails.getPsspCode() : "")
				+ "\",\"psspName\":\""
				+ (tsaTransactionDetails.getPsspName() != null ? tsaTransactionDetails.getPsspName() : "")
				+ "\",\"remittedAmount\":\""
				+ (tsaTransactionDetails.getRemittedAmount() != null ? tsaTransactionDetails.getRemittedAmount() : "")
				+ "\",\"requestedAmount\":\""
				+ (tsaTransactionDetails.getRequestedAmount() != null ? tsaTransactionDetails.getRequestedAmount() : "")
				+ "\",\"sessionId\":\""
				+ (tsaTransactionDetails.getSessionId() != null ? tsaTransactionDetails.getSessionId() : "")
				+ "\",\"gifmisCode\":\""
				+ (tsaTransactionDetails.getGifmisCode() != null ? tsaTransactionDetails.getGifmisCode() : "")
				+ "\",\"tsaPcCoderef\":\""
				+ (tsaTransactionDetails.getTsaPcCoderef() != null ? tsaTransactionDetails.getTsaPcCoderef() : "")
				+ "\",\"tsaPcCodename\":\""
				+ (tsaTransactionDetails.getTsaPcCodename() != null ? tsaTransactionDetails.getTsaPcCodename() : "")
				+ "\",\"valueDate\":\""
				+ (tsaTransactionDetails.getValueDate() != null ? tsaTransactionDetails.getValueDate() : "")
				+ "\",\"whoPays\":\""
				+ (tsaTransactionDetails.getWhoPays() != null ? tsaTransactionDetails.getWhoPays() : "") + "\"}";
		
		LOG.info("PlaceHolder {}", placeHolder);
		return placeHolder;
	}

}