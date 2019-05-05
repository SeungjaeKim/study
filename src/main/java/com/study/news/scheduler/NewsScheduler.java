package com.study.news.scheduler;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.study.news.domain.NewsRssVo;
import com.study.news.service.NewsRssService;

import lombok.extern.log4j.Log4j2;
 
@Log4j2
@Component
public class NewsScheduler {
    
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
     * 5초에 한번씩 수행
     * @throws InterruptedException
     */
    @Scheduled(fixedDelayString = "5000")
    public void job() throws InterruptedException {

        if(!isStart) {
            return;
        }
        
        //compCd = G1C1	-> 한겨례
        //clCd = G2C1   -> 정치
        //String url = "http://www.hani.co.kr/rss/politics";
        
        //뉴스 RSS 정보 조회
        NewsRssVo newsRssVo = new NewsRssVo();
        newsRssVo.setCompCd("G1C1");  //회사코드 - G1C1:한겨례
        List<NewsRssVo> newsRssList = newsRssService.selectNewsRssList(newsRssVo);
        
        for (NewsRssVo newsRss : newsRssList) {
        	
    		try {
    			
    			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(newsRss.getRssUrl());
    			doc.getDocumentElement().normalize();
    			
    			//뉴스 마지막 생성 일시
    			String lastBuildDateStr = doc.getElementsByTagName("lastBuildDate").item(0).getFirstChild().getNodeValue();
    			
    			Date lastBuildDate = new Date(lastBuildDateStr);
    			
    			if (StringUtils.isNotBlank(newsRss.getLastBuildDate()) && 0 > lastBuildDate.compareTo(new Date(newsRss.getLastBuildDate()))) {
    				
    				continue;
    			}
    			
    			
    			NodeList nodeList2 = doc.getElementsByTagName("item");
    			
    			for(int temp = 0; temp < nodeList2.getLength(); temp++) {	
    				
    				Node nNode = nodeList2.item(temp);
    				
    				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
    					
    					Element eElement = (Element) nNode;
    					
    				    Node newsItem = eElement.getElementsByTagName("title").item(0).getFirstChild();
    				    
    				    log.info(newsItem.getNodeValue());
    				}
    			}
    		} catch (ParserConfigurationException | SAXException | IOException e) {
    			e.printStackTrace();
    		}
		}
    }
    
}
