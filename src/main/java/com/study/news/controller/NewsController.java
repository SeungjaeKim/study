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

/**
 * 뉴스 관련
 */
@Controller
public class NewsController {
	
	private static Logger logger = LogManager.getLogger(NewsController.class);

//    @Autowired
//    private TestRepository testRepository;
    
    @Autowired
    private TestService testService;
	
    @GetMapping("/admin/news/main")
    public String main(Model model) {

        return "admin/news/main";
    }
    
    @ResponseBody
    @PostMapping("/admin/news/main/save")
    public HashMap<String, Object> save(Model model) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("111", "222");

        logger.info(testService.getNow());
        
        return map;
    }

}
