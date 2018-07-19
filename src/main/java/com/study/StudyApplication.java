package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class StudyApplication {

	public static void main(String[] args) {
		/**
		 * - 서버 기동시 콘솔에 배너를 출력한다.
		 *     배너기본경로 : /study/src/main/resources/banner.txt
		 */
		SpringApplication.run(StudyApplication.class, args);
	}
}
