package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling       // <-- 스케줄러
public class StudyApplication {

	public static void main(String[] args) {
		/**
		 * - 서버 기동시 콘솔에 배너를 출력한다.
		 *     배너기본경로 : /study/src/main/resources/banner.txt
		 */
		SpringApplication.run(StudyApplication.class, args);
	}
}
