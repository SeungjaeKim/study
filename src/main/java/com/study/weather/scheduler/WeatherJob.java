package com.study.weather.scheduler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
@Component
public class WeatherJob {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 스케줄러 동작 여부 - true:동작, false:동작안함
     */
    @Value("${scheduler.weather.isStart}")
    private Boolean isStart;
    
    @PostConstruct
    public void init() {
        //시작 로그 출력
        logger.info("Start WeatherJob");
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

        logger.info("WeatherJob...");
    }
    
}
