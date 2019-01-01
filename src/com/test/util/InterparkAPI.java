package com.test.util;

import java.io.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.net.*;
import org.json.*;

public class InterparkAPI {

	private String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String bookXml(String isbn) throws ParserConfigurationException, IOException, SAXException {

	 	// Prepare the code below to get Interpark Open API search results
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlObj = null;
		
		String str = String.format("http://book.interpark.com/api/search.api?key=KEY&inputEncoding=utf-8&queryType=isbn&query=%s",
				URLEncoder.encode(isbn, "UTF-8"));
		
		URL url = new URL(str);

		InputSource is = new InputSource(url.openStream());
		xmlObj = builder.parse(is);

		// Create a XML document according to the search (query) result
		String xmlStr = convertDocumentToString(xmlObj);

		return xmlStr;
		
	}
	
}
