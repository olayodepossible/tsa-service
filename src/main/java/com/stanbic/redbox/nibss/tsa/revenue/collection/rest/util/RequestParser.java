package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class RequestParser {

	public static final String XML_NAMESPACE_ALIAS_REGEX = "[\\r\\n\\t\\f\\v ]{0,}(xmlns)?:?([a-zA-Z0-9_]+)?=";

	public static final String XML_NAMESPACE_PREFIX_REGEX = "\\w*?:?";

	public static String getTagFromXmlString(String xmlString, String tagName) {
		String tagNameValueString = null;
		if (xmlString != null && !xmlString.isEmpty()) {
			String patternString = "(<" + XML_NAMESPACE_PREFIX_REGEX + tagName + ".*?>([\\w\\W]*)</"
					+ XML_NAMESPACE_PREFIX_REGEX + tagName + ">)";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(xmlString);
			while (matcher.find()) {
				tagNameValueString = matcher.group(1);
			}
		}
		return tagNameValueString;
	}

	public static String formatXML(String originalXML) {
		return originalXML.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("><", ">\n<").replaceAll("xmlns",
				"\nxmlns");
	}

	public static String getTagValueFromJsonString(String jsonString, String tagName) {
		String tagNameValueString = null;
		if (jsonString != null && !jsonString.isEmpty()) {
			String patternString = "(\"" + tagName + "\".*?:.*?\"(.*?)\")";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(jsonString);
			while (matcher.find()) {
				tagNameValueString = matcher.group(2);
			}
		}
		return tagNameValueString;
	}

	// public static boolean getBooleanFromJsonString(String jsonString, String tagName) {
	// 	if (jsonString == null || tagName == null)
	// 		return false;
	// 	String value = getTagValueFromJsonString(jsonString, tagName).toLowerCase();

           
	// 	switch (value) {
	// 	case "true":
	// 		return true;
	// 	case "false":
	// 		return false;
	// 	default:
	// 		return false;
	// 	}
	// }

	public static int jsonObject(String jsonString, String tagName) {

		String string = getTagValueFromJsonString(jsonString, tagName);
		try {
			int value = Integer.parseInt(string);
			return value;
		} catch (NumberFormatException e) {
			return 0;
		}

	}

	public static String getTagValueFromXmlString(String xmlString, String tagName) {
		String tagNameValueString = null;
		if (xmlString != null && !xmlString.isEmpty()) {
			String patternString = "(<" + XML_NAMESPACE_PREFIX_REGEX + tagName + ".*?>([\\w\\W]*)</"
					+ XML_NAMESPACE_PREFIX_REGEX + tagName + ">)";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(xmlString);
			while (matcher.find()) {
				tagNameValueString = matcher.group(2);
			}
		}
		return tagNameValueString;
	}

	public static String getURIRequestParameterValueByKey(String uriRequestQueryString, String requestParameterKey,
			String requestParameterValueRegex) {
		String requestParameterValue = null;
		String patternString = "(" + requestParameterKey + "=(" + requestParameterValueRegex + "))";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(uriRequestQueryString);
		while (matcher.find()) {
			requestParameterValue = matcher.group(2);
		}
		return requestParameterValue;
	}

	public static void main(String[] args) {
		System.out.println();
				
//		System.out.println();
	}

	public static String objectToXmlString(Object objectToMarshall, Class[] classesToBeBound, boolean removeNamespace,
			String[] namespaces) {
		String xmlString = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(objectToMarshall, stringWriter);
			xmlString = stringWriter.toString();
			stringWriter.close();
			
		} catch (Exception e) {

		}
		return (removeNamespace ? removeNamespacesFromXmlString(xmlString, namespaces) : xmlString);
	}

	
	public static String objectToXml(Object objectToMarshall,Class... _class) {

		String xmlString = null;
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(_class);
			 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(objectToMarshall, stringWriter);
			xmlString = stringWriter.toString();
			stringWriter.close();
			
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
			return xmlString;
		}
		
	}

	public static Object readObjectFromJsonString(String jsonString, Class objectClass) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Object response = mapper.readValue(jsonString, objectClass);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Application Error - Unable To Transform Json String - " + e.getMessage());
		}
	}

	public static String removeNamespacesFromXmlString(String str, String... namespaces) {
		for (String namespace : namespaces) {
			str = str.replaceAll(XML_NAMESPACE_ALIAS_REGEX + namespace, "");
		}
		return str;
	}

	public static String writeObjectAsJsonString(Object object) {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.setSerializationInclusion(Include.NON_NULL);
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			//throw new Exception("Application Error - Unable To Create Json String - " + e.getMessage());
		}

		// jsonString = jsonString.replaceAll("null", "\"\"");
		return jsonString;

	}

	public static String writeObjectAsJsonString(Object object, Class aClass) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		try {
			String jsonString = mapper.writerWithView(aClass).writeValueAsString(object);
			// jsonString = jsonString.replaceAll("null", "\"\"");
			return jsonString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new Exception("Application Error - Unable To Create Json String - " + e.getMessage());
		}
	}

	// public static Object xmlStringToObject(String xmlString, Class<?>... classesToBeBound) throws JAXBException {
	// 	Object object = null;
	
	// 		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
	// 		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	// 		StringReader stringReader = new StringReader(xmlString);
	// 		object = jaxbUnmarshaller.unmarshal(stringReader);
	// 		stringReader.close();
		
	// 	return object;
	// }
	
	public static String writeJsonString(Object object) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(object);
			jsonString = jsonString.replaceAll("null", "\"\"");
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception( "Application Error - Unable To Create Json String - " + e.getMessage());
		}
	}
	

	/**
	 * @author C807531 Gets the body of an xml tag and convert to json
	 * @param xmlString
	 * @param tagName
	 */
	/*public static JSONObject xmlToJson(String xmlString, String tagName) {
		org.jsoup.nodes.Document doc = Jsoup.parse(xmlString, "", Parser.xmlParser());

		StringBuilder sb = new StringBuilder();
		for (org.jsoup.nodes.Element e : doc.select(tagName)) {

			sb.append(e.toString());
		}

		return XML.toJSONObject(sb.toString());
	}
*/
	public static String encodeUrl(String url) throws UnsupportedEncodingException {
		if (url == null)
			return "";
		return URLEncoder.encode(url, "UTF-8");
	}
	
	 
    /**
     *@param obj The object to be converted to a soap envelope
     *@param rootElement The root element of the soap body
     *@param nameSpace the namespace of the soap body
     *@param nameSpaceURI the namespace URI
     */
	/*
    public static String  createSoapEnvelope(Object obj,String rootElement,String nameSpace,String nameSpaceURI,String soapAction) 
    		throws SOAPException, IOException, IllegalArgumentException, IllegalAccessException {
    	
    	System.out.println(nameSpace);
    	System.out.println(nameSpaceURI);
    	 MessageFactory messageFactory = MessageFactory.newInstance();
      	 SOAPMessage soapMessage = messageFactory.createMessage();
    	
        SOAPPart soapPart = soapMessage.getSOAPPart();


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        
        envelope.addNamespaceDeclaration(nameSpace, nameSpaceURI);

        SOAPBody soapBody = envelope.getBody();
        
       
        SOAPElement soapBodyRootElement = soapBody.addChildElement(rootElement, nameSpace);
        
        //Use reflection to get declared fields
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field f : fields) {
        	
       	 f.setAccessible(true);
       	 XmlElement a = f.getAnnotation(XmlElement.class);
       	 String nameValue = a.name();

       	 String val  = (String)f.get(obj);
       	 
       	 if(val != null) {
       	SOAPElement soapBodyRootElement2 = soapBody.addChildElement(nameValue, nameSpace);
       	soapBodyRootElement2.addTextNode((String)f.get(obj));
       	soapBodyRootElement.addChildElement(soapBodyRootElement2);
       	 }

        }
      
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        
        soapMessage.saveChanges();

        // Print the request message, just for debugging purposes 
        System.out.println("Request SOAP Message:");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        String strMsg = new String(out.toByteArray());

        System.out.println(strMsg);
        
        return strMsg;
      
    }
*/
	
	
}
