package com.study;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.study.news.domain.NewsRssVo;
import com.study.news.service.NewsRssService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsSchedulerTests {

    @Autowired
    private NewsRssService newsRssService;
	
    /**
     * 스케줄러 동작 여부 - true:동작, false:동작안함
     */
    @Value("${scheduler.news.isStart}")
    private Boolean isStart;
    
    @PostConstruct
    public void init() {
        //시작 로그 출력
        log.info("Start News Job");
    }

    /**
     * 1초에 한번씩 수행
     * @throws InterruptedException
     */
    @Test
//    @Scheduled(fixedDelayString = "5000")
    public void job() throws InterruptedException {

        if(!isStart) {
            return;
        }
        
        //compCd = G1C1	-> 한겨례
        //clCd = G2C1   -> 정치

        //뉴스 RSS 정보 조회
        NewsRssVo newsRssVo = new NewsRssVo();
        newsRssVo.setCompCd("G1C1");  //회사코드 - G1C1:한겨례
        List<NewsRssVo> newsRssList = newsRssService.selectNewsRssList(newsRssVo);
        
        log.info("URL : " + newsRssList.get(0).getRssUrl());
         
		String url = "http://www.hani.co.kr/rss/politics";
		
		try {
		
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			
			log.info("doc ~~~~~~~~~~~~~~~~~~~~~~~");
			
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

        log.info("NewsJob...");
    }

}
