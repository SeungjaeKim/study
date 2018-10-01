package com.study.news.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
 
@Log4j2
@Component
public class NewsScheduler {
    
    /**
     * 스케줄러 동작 여부 - true:동작, false:동작안함
     */
    @Value("${scheduler.news.isStart}")
    private Boolean isStart;
    
    @PostConstruct
    public void init() {
        //시작 로그 출력
        log.info("Start NewsJob");
    }

    /**
     * 1초에 한번씩 수행
     * @throws InterruptedException
     */
    @Scheduled(fixedDelayString = "1000")
    public void job() throws InterruptedException {

        if(!isStart) {
            return;
        }

        log.info("NewsJob...");
    }
    
}
