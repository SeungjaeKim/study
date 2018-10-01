package com.study.news.controller;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.news.domain.NewsRssVo;
import com.study.news.service.NewsRssService;

import lombok.extern.log4j.Log4j2;

/**
 * 뉴스 관련
 */
@Log4j2
@Controller
public class NewsController {
	
    @Autowired
    private NewsRssService newsRssService;
	
    @GetMapping("/admin/news/main")
    public String main(Model model) {

        return "admin/news/main";
    }
    
    @ResponseBody
    @PostMapping("/admin/news/main/save")
    public HashMap<String, Object> save(Model model) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("111", "222");

        log.info("getNewsRssList  :" + newsRssService.getNewsRssList().size());
        
        NewsRssVo nr = new NewsRssVo();
        
        return map;
    } 

}
