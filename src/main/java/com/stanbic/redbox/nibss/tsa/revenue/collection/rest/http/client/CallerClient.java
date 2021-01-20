package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.http.client;


import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.exception.RedBoxTSARevenueCollectionException; 
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.ResponseCode;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util.NibssTSARevenueCollectionConstant;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.GenericHTTPRequestObject;
import com.stanbic.redbox.nibss.tsa.revenue.collection.rest.dto.GenericHTTPResponseObject;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.conn.params.ConnRoutePNames;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.camel.Exchange;
import org.apache.http.Header;
import org.slf4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.apache.http.HttpHost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class CallerClient {

    private boolean shouldAppBypassCertificate;
    private String storePwd;
    private String storePathway;
    private String username;

    public boolean isShouldAppBypassCertificate() {
        return shouldAppBypassCertificate;
    }

    public void setShouldAppBypassCertificate(boolean shouldAppBypassCertificate) {
        this.shouldAppBypassCertificate = shouldAppBypassCertificate;
    }

    public String getStorePwd() {
        return storePwd;
    }

    public void setStorePwd(String storePwd) {
        this.storePwd = storePwd;
    }

    public String getStorePathway() {
        return storePathway;
    }

    public void setStorePathway(String storePathway) {
        this.storePathway = storePathway;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private static final Logger log = LoggerFactory
            .getLogger(CallerClient.class);
 

    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public String stringify(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (final byte b : bytes) {
            sb.append(hex[(b & 0xF0) >> 4]);
            sb.append(hex[b & 0x0F]);
        }
        return sb.toString();
    }

    public String encode(String detail) throws RedBoxTSARevenueCollectionException {
        byte[] bean = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(detail.getBytes());
            bean = messageDigest.digest();
        } catch (Exception e) {
            String msg = "NIBSS TSA COLLECTION NOTIFICATION: Error encoding token for nibss..."
                    + e.getMessage();
            log.info(msg, e);
            RedBoxTSARevenueCollectionException ex = new RedBoxTSARevenueCollectionException();
            ex.setResponseCode(ResponseCode.ENCRYPTION_FAILURE
                    .getCode());
            ex.setResponseDescription(msg);
            throw ex;
        }
        return stringify(bean);
    }

    public GenericHTTPResponseObject sendHTTPGetRequest( String url, String proxyHost,
            int port, String contentType, String encoding,
            boolean isOverProxy, Exchange exchange, String authorization) throws RedBoxTSARevenueCollectionException {
    	MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
        String httpResponseMessage = "";
        String httpResponseCode = "";
        String httpResponseCodeReasonPhrase = "";
        MDC.put(NibssTSARevenueCollectionConstant.APP_NAME, exchange.getProperty(NibssTSARevenueCollectionConstant.APP_NAME, String.class));
        log.debug("nibss TSA:haproxy http-call");
        boolean isThereHTTPException = false;
        String exception = "";
        long contentLength = -1;
        GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        
        try {
            if (isOverProxy == true) {
                HttpHost proxy = new HttpHost(proxyHost, port);
                httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(new BasicHeader("Content-Type", contentType));
            httpGet.setHeader(new BasicHeader("Authorization", authorization));
            
            
//            HttpPost httpPost = new HttpPost(url);
////            exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_REQUEST_PAYLOAD, genericHTTPRequestObject.toString());
//            exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_REQUEST_TIMESTAMP, new Date());
//            httpPost.setHeader(new BasicHeader("SOAPAction", soapAction));
//            httpPost.setHeader(new BasicHeader("Content-Type", contentType));
//            StringEntity s = new StringEntity(httpRequestMessage, encoding);
//            httpPost.setEntity(s);
            HttpResponse response = httpclient.execute(httpGet);
            
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                httpResponseMessage = EntityUtils.toString(response.getEntity());
                contentLength = response.getEntity().getContentLength();
                httpResponseCode = response.getStatusLine().getStatusCode() + "";
                httpResponseCodeReasonPhrase = response.getStatusLine().getReasonPhrase();
                entity.consumeContent();
                responseObject.setException(exception);
                responseObject.setHttpResponseCode(httpResponseCode);
                responseObject.setHttpResponseMessage(httpResponseMessage);
                responseObject.setThereHTTPException(isThereHTTPException);
                responseObject.setContentLength(contentLength);
                responseObject.setHttpResponseCodeReasonPhrase(httpResponseCodeReasonPhrase);

                exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_RESPONSE_PAYLOAD, responseObject.toString());
                exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_RESPONSE_TIMESTAMP, new Date());
            }

        } catch (IOException | ParseException e) {
            isThereHTTPException = true;
            e.printStackTrace();
            exception = e.toString();
            // log.info("Response code from nibss on " + url + " endpoint: " + httpResponseCode + "|" + httpResponseMessage);
            String msg = "SimSwap: Error calling nibss  on " + url + " endpoint: " + e.getMessage();
            log.info(msg, e);
            RedBoxTSARevenueCollectionException ex = new RedBoxTSARevenueCollectionException();
            ex.setResponseCode(ResponseCode.VENDOR_COMMUNICATION_ERROR.getCode());
            ex.setResponseDescription(msg);
            throw ex;

        } finally {

        }
        responseObject.setException(exception);
        responseObject.setHttpResponseCode(httpResponseCode);
        responseObject.setHttpResponseMessage(httpResponseMessage);
        responseObject.setThereHTTPException(isThereHTTPException);
        responseObject.setContentLength(contentLength);
        responseObject.setHttpResponseCodeReasonPhrase(httpResponseCodeReasonPhrase);
        return responseObject;
    }
    public GenericHTTPResponseObject sendHTTPRequest(String httpRequestMessage, String url, String proxyHost,
            int port, String contentType, String encoding,
            boolean isOverProxy, Exchange exchange, String authorization) throws RedBoxTSARevenueCollectionException {
    	MDC.put("app.name", exchange.getProperty("APP_NAME", String.class));
        String httpResponseMessage = "";
        String httpResponseCode = "";
        String httpResponseCodeReasonPhrase = "";
        String authorizationToken ="";
        MDC.put(NibssTSARevenueCollectionConstant.APP_NAME, exchange.getProperty(NibssTSARevenueCollectionConstant.APP_NAME, String.class));
        log.debug("TSA Revenue Collection Notification:haproxy http-call");
        boolean isThereHTTPException = false;
        String exception = "";
        long contentLength = -1;
        GenericHTTPResponseObject responseObject = new GenericHTTPResponseObject();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            if (isOverProxy == true) {
                HttpHost proxy = new HttpHost(proxyHost, port);
                httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            HttpPost httpPost = new HttpPost(url);
//            exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_REQUEST_PAYLOAD, genericHTTPRequestObject.toString());
            exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_REQUEST_TIMESTAMP, new Date());
            httpPost.setHeader(new BasicHeader("Authorization", authorization));
            httpPost.setHeader(new BasicHeader("Content-Type", contentType));
            StringEntity s = new StringEntity(httpRequestMessage, encoding);
            httpPost.setEntity(s);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                httpResponseMessage = EntityUtils.toString(response.getEntity());
                contentLength = response.getEntity().getContentLength();
                httpResponseCode = response.getStatusLine().getStatusCode() + "";
                httpResponseCodeReasonPhrase = response.getStatusLine().getReasonPhrase(); 
                entity.consumeContent(); 
                
                Header header = response.getLastHeader("Authorization");
                log.info("The header gotten is:\n{}", header);
                if(header != null && httpResponseCode.contains("200")){
                	log.info("The header Value gotten is:\n{}", header.getValue());
                    exchange.setProperty(NibssTSARevenueCollectionConstant.NIBSS_AUTHORIZATION, header.getValue());
                }
                
                responseObject.setException(exception);
                responseObject.setHttpResponseCode(httpResponseCode);
                responseObject.setHttpResponseMessage(httpResponseMessage);
                responseObject.setThereHTTPException(isThereHTTPException);
                responseObject.setContentLength(contentLength);
                responseObject.setHttpResponseCodeReasonPhrase(httpResponseCodeReasonPhrase);
                
                exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_RESPONSE_PAYLOAD, responseObject.toString());
                exchange.setProperty(NibssTSARevenueCollectionConstant.PRODUCER_RESPONSE_TIMESTAMP, new Date());
            }

        } catch (Exception e) {
            isThereHTTPException = true;
            e.printStackTrace();
            exception = e.toString();
            // log.info("Response code from nibss on " + url + " endpoint: " + httpResponseCode + "|" + httpResponseMessage);
            String msg = "TSA Revenue Collection: Error calling nibss on " + url + " endpoint: " + e.getMessage();
            log.info(msg, e);
            RedBoxTSARevenueCollectionException ex = new RedBoxTSARevenueCollectionException();
            ex.setResponseCode(ResponseCode.VENDOR_COMMUNICATION_ERROR.getCode());
            ex.setResponseDescription(msg);
            throw ex;

        } finally {

        }
      /**
        responseObject.setException(exception);
        responseObject.setHttpResponseCode(httpResponseCode);
        responseObject.setHttpResponseMessage(httpResponseMessage);
        responseObject.setThereHTTPException(isThereHTTPException);
        responseObject.setContentLength(contentLength);
        responseObject.setHttpResponseCodeReasonPhrase(httpResponseCodeReasonPhrase);
        **/
        
        return responseObject;
    } 
    private static SSLContext createSslContext(String keyStorePath, String keyStorePassword, boolean bypassCertificateCheck) throws Exception {
        // Create a KeyStore
        FileInputStream fis = null;
        SSLContext sslContext = null;
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            fis = new FileInputStream(keyStorePath);
            keystore.load(fis, keyStorePassword.toCharArray());
            fis.close();
            // Create a KeyManager
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keystore, keyStorePassword.toCharArray());
            KeyManager[] keystoreManagers = kmf.getKeyManagers();
            // Create a TrustManager from the KeyStore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            // tmf.init((KeyStore)null);
            tmf.init(keystore);
            TrustManager[] keyStoreTrustManagers = tmf.getTrustManagers();
            // Create a TrustManager that bypasses certificate validation
            TrustManager[] bypassTrustManagers = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }
            }};
            sslContext = SSLContext.getInstance("TLS");
            //System.out.println("isBypassCertificateCheckFlag: " 	+ bypassCertificateCheck);
            if (bypassCertificateCheck) {
                sslContext.init(keystoreManagers, bypassTrustManagers, new SecureRandom());
            } else {
                sslContext.init(keystoreManagers, keyStoreTrustManagers, new SecureRandom());
            }

        } catch (Exception e) {
            String msg = "NIBSS TSA COLLECTION NOTIFICATION: InnerException " + e.toString();
            log.info(msg, e);
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    String msg = "NIBSS TSA COLLECTION NOTIFICATION: InnerException " + e.toString();
                    log.info(msg, e);
                }
            }
        }
        return sslContext;
    }

    //calling plain HTTP
    public String sendHTTPRequest(String httpRequestMessage, String url, String proxyHost,
            int port, String soapAction, String contentType, String encoding, boolean isOverProxy) {
        String httpResponseMessage = "";
        try {

            DefaultHttpClient httpclient = new DefaultHttpClient();
            if (isOverProxy == true) {
                HttpHost proxy = new HttpHost(proxyHost, port);
                httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(new BasicHeader("Content-Type", contentType));
            httpPost.setHeader(new BasicHeader("SOAPAction", soapAction));
            StringEntity s = new StringEntity(httpRequestMessage, encoding);
            httpPost.setEntity(s);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                httpResponseMessage = EntityUtils.toString(response.getEntity());
            }
            if (entity != null) {
                entity.consumeContent();
            }
        } catch (Exception e) {
            System.out.println("Exception e" + e.toString());
            e.printStackTrace();
        }
        return httpResponseMessage;
    }

    public static void main(String[] a) throws Exception {
        new CallerClient().sendHTTPRequest("test", "http://10.234.148.145/Wealthbox/Siaml/Integrator.asmx", "10.234.135.39", 8080,
                "\"http://tempuri.org/iBranchNameValidation\"",
                "text/xml;charset=UTF-8", "UTF-8", false);
    }
 
}
