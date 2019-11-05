package com.study.admin.scheduler;

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

import com.study.admin.news.domain.NewsRssUrlVo;
import com.study.admin.news.domain.NewsVo;
import com.study.admin.news.service.NewsRssUrlAdmService;
import com.study.admin.news.service.NewsAdmService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class NewsScheduler {

	@Autowired
	private NewsAdmService newsAdmService;

    @Autowired
    private NewsRssUrlAdmService newsRssUrlAdmService;

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
     * 10초에 한번씩 수행
     * @throws InterruptedException
     */
    @Scheduled(fixedDelayString = "10000")  //스케줄러 동작 완료 후 설정된 시간뒤에 재실행
    public void job() throws InterruptedException {

        if(!isStart) {
            return;
        }

        //compCd = G1C1	-> 한겨례
        //clCd = G2C1   -> 정치
        //String url = "http://www.hani.co.kr/rss/politics";

        //뉴스 RSS 정보 조회
        NewsRssUrlVo newsRssVo = new NewsRssUrlVo();
        newsRssVo.setCompCd("G1C1");  //회사코드 - G1C1:한겨례
        List<NewsRssUrlVo> newsRssList = newsRssUrlAdmService.selectNewsRssList(newsRssVo);

        for (NewsRssUrlVo newsRssUrl : newsRssList) {

    		try {

    			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(newsRssUrl.getRssUrl());
    			doc.getDocumentElement().normalize();

    			//뉴스 마지막 생성 일시
    			String lastBuildDateStr = doc.getElementsByTagName("lastBuildDate").item(0).getFirstChild().getNodeValue();

    			Date lastBuildDate = new Date(lastBuildDateStr);

    			if (StringUtils.isNotBlank(newsRssUrl.getLastBuildDate()) && 0 >= lastBuildDate.compareTo(new Date(newsRssUrl.getLastBuildDate()))) {

    				continue;
    			}

    			NodeList itemList = doc.getElementsByTagName("item");

    			for (int i = 0; i < itemList.getLength(); i++) {

    				Node item = itemList.item(i);

    				if(item.getNodeType() == Node.ELEMENT_NODE) {

    					Element eElement = (Element) item;

    				    Node titleNode = eElement.getElementsByTagName("title").item(0).getFirstChild();
    				    Node linkNode = eElement.getElementsByTagName("link").item(0).getFirstChild();
    				    Node pubDateNode = eElement.getElementsByTagName("pubDate").item(0).getFirstChild();

    				    log.debug(titleNode.getNodeValue());

    				    //뉴스 등록
    				    NewsVo newsVo = new NewsVo();
    				    newsVo.setCompCd("G1G2");                       //언론사 코드
    				    newsVo.setClCd("G2C1");                         //분야 코드
    				    newsVo.setPubDt(pubDateNode.getNodeValue());    //공개 일시
    				    newsVo.setNewsUrl(linkNode.getNodeValue());     //뉴스 주소
    				    newsVo.setNewsTitle(titleNode.getNodeValue());  //뉴스 제목
    				    newsAdmService.insertNews(newsVo);
    				}
    			}

    			//RSS 마지막 생성일시 갱신
    			newsRssUrl.setLastBuildDate(lastBuildDateStr);  //RSS 마지막 생성 일시
    			newsRssUrlAdmService.updateLastBuildDateOfNewsRss(newsRssUrl);
    		} catch (ParserConfigurationException | SAXException | IOException e) {
    			e.printStackTrace();
    		}
		}
    }

}
