package com.kirat.solutions.processor;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import com.kirat.solutions.Constants.BinderConstants;
import com.kirat.solutions.domain.BinderList;
import com.kirat.solutions.domain.Children;

public class TransformationProcessor {

	public BinderList processHtmlToBinderXml (String htmlContent) throws TransformerException, JsonParseException, JsonMappingException, IOException{
		String sucessMsg = BinderConstants.SUCCESS_MSG;
		BinderList binderObject = null;
		/*InputStream contentStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));

		 TransformerFactory factory = TransformerFactory.newInstance();
	        Source xslt = new StreamSource(new File("/java-standalone/Xslt/TestXsl.xsl"));
	        Transformer transformer = factory.newTransformer(xslt);

	        Source text = new StreamSource(contentStream);
	        //create a xml file structure
	        transformer.transform(text, new StreamResult(new File("/java-standalone/Xslt/FileIt Sample Tree Structure.out.xml")));

		return sucessMsg;*/

		//htmlContent = "[{\"id\": 1,\"name\": \"root1\",\"classification\": \"Math&\",\"children\": [{\"id\": 2,\"name\": \"child1\",\"path\": \"E:/abc.pdf\",\"type\": \"Application/PDF\",\"version\": \"1.0\",\"note\":\"This is a note\"}]}]";
		// [{\\"nodes\\": [{\\"id\\": 1,\\"name\\": \\"root1\\",\\"classification\\": \\"Math\\",\\"children\\": [{\\"id\\": 2,\"name\\": \\"child1\\",\\"path\\": \\"E:abc.pdf\\",\\"Type\\": \\"Application/PDF\\",\\"version\\": \\"1.0\\",\\"note\\":\\"This is a note\\"},{\\"id\\": 3,\\"name\\": \\"child2\\",\\"path\\": \\"E:abcd.pdf\\",\\"Type\\": \\"Application/PDF\\",\\"version\\": \\"1.0\\",\\"note\\":\\"This is a note\\"}]}]}]
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(htmlContent);
		jp.nextToken();
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			//System.out.println(jp.nextToken().name());
		binderObject = objectMapper.readValue(jp, BinderList.class);
		/*System.out.println(binderObject.getName());
		System.out.println(binderObject.getClassification());
		System.out.println(binderObject.getId());*/
		}
		prepareBinderXML(binderObject);
		return binderObject;
	}

	public static void prepareBinderXML(BinderList binderlist) {
		final String xmlFilePath = "D:\\files\\xmlfile.xml";
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			org.w3c.dom.Document document = documentBuilder.newDocument();
			// root element
			Element root = document.createElement("map");
			document.appendChild(root);
			String uniqueID = UUID.randomUUID().toString();
			root.setAttribute("id", uniqueID);

			// employee element
			Element title = document.createElement("title");
			title.setNodeValue(binderlist.getName());
			root.appendChild(title);

			Element body = document.createElement("body");
			root.appendChild(body);
			Element topicref = document.createElement("topicref");
			body.appendChild(topicref);

			// set an attribute to topicref element
			Attr attr = document.createAttribute("navtitle");
			attr.setValue(binderlist.getName());
			Attr type = document.createAttribute("type");
			type.setValue(BinderConstants.BINDER);
			Attr classification = document.createAttribute("classification");
			classification.setValue(binderlist.getClassification());
			topicref.setAttributeNode(attr);
			topicref.setAttributeNode(type);
			topicref.setAttributeNode(classification);
			for(Children child : binderlist.getChildren()) {
				Element topic = document.createElement("topic");
				topic.setAttribute(BinderConstants.NAME, child.getName());
				topic.setAttribute(BinderConstants.PATH, child.getPath());
				topic.setAttribute(BinderConstants.TYPE, child.getType());
				topic.setAttribute(BinderConstants.VERSION, child.getVersion());
				topic.setAttribute(BinderConstants.ID, (String.valueOf(child.getId())));
				topicref.appendChild(topic);
			}
			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			// StreamResult result = new StreamResult(System.out);
			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		}catch(Exception e) {
			e.printStackTrace();
		}



	}

}
