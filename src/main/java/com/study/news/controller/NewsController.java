package com.study.news.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.news.domain.NewsRssVo;
import com.study.news.service.CommCodeService;
import com.study.news.service.NewsRssService;

import lombok.extern.log4j.Log4j2;

/**
 * 뉴스 관련
 */
@Log4j2
@Controller
public class NewsController {
	
	@Autowired
    private CommCodeService commCodeService;
	
    @Autowired
    private NewsRssService newsRssService;
	
    @GetMapping("/admin/news/main")
    public String main(Model model) {
    	
    	//공통코드 목록 조회 - G1:뉴스 언론사
    	model.addAttribute("newsCompCdList", commCodeService.selectCommCodeList("G1"));
    	
    	//공통코드 목록 조회 - G2:뉴스 분야
    	model.addAttribute("newsClCdList", commCodeService.selectCommCodeList("G2"));
    	
        return "admin/news/main";
    }
    
    @ResponseBody
    @PostMapping("/admin/news/main/save")
    public HashMap<String, Object> save(NewsRssVo newsRssVo) {

    	//뉴스 RSS 등록
    	newsRssService.insertNewsRssList(newsRssVo);
    	
        HashMap<String, Object> map = new HashMap<>();
        
        return map;
    } 

}
