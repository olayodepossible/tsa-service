package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception.RedBoxTSARevenueCollectionException;

public class AESCrypter {

	//String initVector = "C60g4Y4qakFIRK_s";
	//String secretKey  = "CtXJQ__i5lUzX2GV";
	private static final Logger infoLogger = LoggerFactory.getLogger(AESCrypter.class);

	private String iv ; 
	private String secretkey ;
	private IvParameterSpec ivspec;
	private SecretKeySpec keyspec;
	private Cipher cipher;

	//"px":"hXw7o9$gp%Zs)F%^","rx":"rQ)g)Us9wlm&vwH1"
	public AESCrypter(String keyz, String ivStr) {		
		secretkey = keyz;
		iv = ivStr;
		ivspec = new IvParameterSpec(ivStr.getBytes());
		keyspec = new SecretKeySpec(keyz.getBytes(), "AES");
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public AESCrypter(String keyz, String ivStr,String placeHolder) {		
		this.secretkey = keyz;
		this.iv = ivStr;		 
	}
	
	public AESCrypter() {
		ivspec = new IvParameterSpec(iv.getBytes());
		keyspec = new SecretKeySpec(secretkey.getBytes(), "AES");
		System.out.println("this ivspec = " + ivspec);
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String text) {
		//System.out.println("text = " + text);
		if (text == null || text.length() == 0) {
			return "";
		}
		byte[] encrypted = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			encrypted = cipher.doFinal(text.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
		return bytesToHex(encrypted);
	}

	public String decrypt(String code) throws UnsupportedEncodingException {
		if (code == null || code.length() == 0) {
			return "";
		}
		byte[] decrypted = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			decrypted = cipher.doFinal(hexToBytes(code));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return new String(decrypted, "UTF-8");
	}

	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		}
		int len = data.length;
		String str = "";
		for (int i = 0; i < len; i++) {
			if ((data[i] & 0xFF) < 16) {
				str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
			} else {
				str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
		}
		return str;
	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	
	public String encryptJsonRequestBody(String requestString)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,  RedBoxTSARevenueCollectionException {
		infoLogger.info("Inside encryptJsonRequestBody");
		String initVector = this.iv;
		String secretKey = this.secretkey;

		infoLogger.info("initVector : {}" , initVector);
		infoLogger.info("secretKey : {}" , secretKey);
		
		IvParameterSpec ivspec = new IvParameterSpec(initVector.getBytes());
		SecretKeySpec keyspec = new SecretKeySpec(secretKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String exchangeMessage = requestString;

		if (exchangeMessage == null || exchangeMessage.length() == 0) {
			throw new RedBoxTSARevenueCollectionException("96", "The exchange body is null");
		}
		infoLogger.info("RAW STRING: {}", exchangeMessage);
		byte[] encryptedExchangeMessage = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			encryptedExchangeMessage = cipher.doFinal(exchangeMessage.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RedBoxTSARevenueCollectionException("96", "Message Security Failed - [ENC]");
		}
		String encryptedExchangeMessageString = DatatypeConverter.printHexBinary(encryptedExchangeMessage);
		infoLogger.info("ENC STRING: {}", encryptedExchangeMessageString.toLowerCase());
		return encryptedExchangeMessageString.toLowerCase();
	}
	
	
	
	public static void main(String[] args) {
//Old credentials
		// String iv = "84:86vv13ygJRfQ)"; //M&aW1NHf9s30Fial
		// String secretkey = "@2v7aL)evQ8~cGBb";//
		 
		 String iv = "MvO*~Ybk(izFicJ#"; //
		 String secretkey = "ja!(qAr!Q44g%g@3";//Z$aVXMB~@DN876@(
		 
		 
		 
		 
		// "px":"Z$aVXMB~@DN876@(","rx":"M&aW1NHf9s30Fial"
		// SecrtKy : ikzWkQNXvk$Hth@X
        // Tokn : o27UAX^rO181jjWp
		AESCrypter aesCrypter = new AESCrypter(secretkey, iv); 
		String req = "{\r\n" + 
				"   \"uniqueReference\":\"FCM05214855980986847\",\r\n" + 
				"   \"bankBranchid\":\"214150087\",\r\n" + 
				"   \"bankId\":\"214\",\r\n" + 
				"   \"batchId\":\"FCM21435763252135445\",\r\n" + 
				"   \"branchPhoneno\":\"08055907843\",\r\n" + 
				"   \"bvn\":\"null\",\r\n" + 
				"   \"cbnAcct\":\"0560861410\",\r\n" + 
				"   \"channel\":\"05\",\r\n" + 
				"   \"collectedAmount\":\"100.00\",\r\n" + 
				"   \"currency\":\"NGN\",\r\n" + 
				"   \"customerAccount\":\"\",\r\n" + 
				"   \"customerEmail\":\"\",\r\n" + 
				"   \"customerName\":\"ADEGBITE, KAZEEM ADESHINA\",\r\n" + 
				"   \"customerPhone\":\"07080287357\",\r\n" + 
				"   \"customerTin\":\"00775737-0001\",\r\n" + 
				"   \"fee\":\"0.001\",\r\n" + 
				"   \"feedType\":\"C\",\r\n" + 
				"   \"gifmisCode\":\"null\",\r\n" + 
				"   \"itemCode\":\"\",\r\n" + 
				"   \"itemName\":\"\",\r\n" + 
				"   \"locationCode\":\"293\",\r\n" + 
				"   \"locationName\":\"293\",\r\n" + 
				"   \"mdaCode\":\"0111001001000\",\r\n" + 
				"   \"mdaName\":\"STATE HOUSE - HQTRS\",\r\n" + 
				"   \"narrationDesc\":\"01072020 STAMP DUTY CHARGE - 01/07/2020\",\r\n" + 
				"   \"payColDate\":\"2020-07-01-00-00-00\",\r\n" + 
				"   \"psspCode\":\"FCM\",\r\n" + 
				"   \"psspName\":\"First City Monument Bank\",\r\n" + 
				"   \"remittedAmount\":\"100.00\",\r\n" + 
				"   \"requestedAmount\":\"100.00\",\r\n" + 
				"   \"sessionId\":\"1\",\r\n" + 
				"   \"settlementRef\":\"C0010P09576\",\r\n" + 
				"   \"tsaPcCodename\":\"STATE HOUSE MEDICAL CENTER\",\r\n" + 
				"   \"tsaPcCoderef\":\"0111001001000-C0010-P09576\",\r\n" + 
				"   \"valueDate\":\"01-07-2020\",\r\n" + 
				"   \"whoPays\":\"ME\"\r\n" + 
				"}";

		String mdaReq ="{\"mdacode\":\"000REEE8\"}";
		
		String col = "{\"mdacode\":\"0111009001000\"}";
		
		
		
		
		try {
			 String encr = aesCrypter.encryptJsonRequestBody(req);
				//	52da59a69f3826cb8726ccfac85bdb8a4fdb4b722ceb0d8b112fdc544e368a07d202040317dbdcf3c1566f3d86a3016a
			
			//1.david d0a35a1f9453efdaf0e07d2ee2e7a8bcc1025553e1281e875085b38150fe4445
			 //2. d0a35a1f9453efdaf0e07d2ee2e7a8bc595bb4b11b153300f2aecf93545c5634
			 //3. d0a35a1f9453efdaf0e07d2ee2e7a8bc94e955c922331bc7c7858519d990462d
			  System.out.println(encr);
			
			System.out.println(aesCrypter.decrypt("3ec7d321c1f56136bd77d7bce413c8293b5812a6270fdbc93473e8740c5638860e496d78b8c24af70dc623b4e9183db15c3c47195c196dc011c7f28e7250ec7e526414775443f4d2e257bdf466cb363e268cf284ad89818ee88cc81bb26bbbe9918242a3070e3a2772ba810c7189b1bbae3eccc08821e6ecead7ae0c6aaddd9ded46708241a3c32f223452c8c5cef6b209e7daf0083dd9a76e1832a762d1f1197bb602570f111b24db947e821e5c04a90fcbc2935a6803aee59e78d15156cfd96fb7862bd877ca987a6beeed0a74e7160fe62c35f1a2d934ec04a9f79b3cb7ef5d65cf77f2791ea36876f451b5a769e6aa4da33f324c595b3ce1ff0a2ef7b2b67cc727c9171bfe6c7e5cb559f29265f9c30099122b724fcb66fbc6f210b2bbe75020063bfab8266c5331ab7d6590b1fef36e1a860a8c3aac898865a46eb887baaac7661504fd95d6305642005cf2c9f7ae8a1cb829bff76a3cbc762947c3dec4f3260058ae59e02294811eace07318b4fc33f5f94d5b73a0b3c2a6ae951042728aff51065f4aa76c00f53d13de34c8376bf0a80f9dfd31ff516fb87af1a24cb8ceea5626d115a8c3db992f1f16698b3435c8dfce002cece66aa54e49187bdd62262fd0899597fe93809e01516a34266ce5272823146cbb772dd201014b7f2c546dbfceb83c9dece91843dd615c0738ff29609a8cae9277665df58944b53ae37a882daab00dbc4f04296aa08920ca15cdc666ec92927ff7fd7dac8dff78a189766a8dbd6af0c670a1e0779db8f93db50682391bf6c95dcedd07bd1b6662ca26dfdcf264a682bf9b2ea93f93478d2178be77175a8ef68c2feadb641e13d34c556713ecc3204ff14cd80c440de7d8b057f1648d79ee9ddf979110f3223f1453bf3d21e45e2522da6a8199f1d533e4834337988baf7be297f907154082da6e308de5ced26578dab4fc836d98505c1156c20ecc34990e631379442ba85a5065f3f0cb67abd0b8de41978612617e42606741bd1219722598910ef7ec7478057767a8c35c49a43ec4a849c6d95352631f797c5d8c3d3571505678eee7ead1c1d154f020623da47c0c81accdcd9aa60a9a166ee5e063d1a3496020b07e7c84ceaaed3430fcb930e0d37b8c31f7a72e5fa89b42875e7f4fc6a0fe9e47e88f57bbd5073f34a8a62aeaa6bc18ba717abcf2d05f74a8aaa900efdddddcc8c34cdf2bba67efa07ce81819a84debe541863fabffdce8574e37fcbef61d9572c3e08a99313e7b5544d07dead8163303a0f05fb82e2d222064ec42120a2d6cd5a875ca28fac20f7c616cb24b0d20677ed0a7098734e6750824a9148ce5ee45800ba025481f05a617a55ea53f0644e9611d6a3931c48833dd"));
			
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
}
