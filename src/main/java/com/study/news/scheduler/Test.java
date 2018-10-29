package com.study.news.scheduler;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "http://www.hani.co.kr/rss/politics";
		
		try {
		
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); 

			
			Node lastBuildDate = doc.getElementsByTagName("lastBuildDate").item(0).getFirstChild();
			
			log.info(lastBuildDate.getNodeValue());
			
			NodeList nodeList2 = doc.getElementsByTagName("item");
			
			for(int temp = 0; temp < nodeList2.getLength(); temp++) {	
				
				Node nNode = nodeList2.item(temp);
				
				
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					
				    Node newsItem = eElement.getElementsByTagName("title").item(0).getFirstChild();
				    
				    log.info(newsItem.getNodeValue());
				}
			}
			
			log.info("end");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
