package com.study.admin.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.admin.comm.service.CommCodeAdmService;
import com.study.admin.news.domain.NewsRssUrlVo;
import com.study.admin.news.service.NewsRssUrlAdmService;

import lombok.extern.log4j.Log4j2;

/**
 * 뉴스 관련
 */
@Log4j2
@Controller
public class NewsAdmController {

	@Autowired
    private CommCodeAdmService commCodeAdmService;

    @Autowired
    private NewsRssUrlAdmService newsRssService;

    @GetMapping("/admin/news/main")
    public String main(Model model, NewsRssUrlVo newsRssVo) {

    	//공통코드 목록 조회 - G1:뉴스 언론사
    	model.addAttribute("newsCompCdList", commCodeAdmService.selectCommCodeList("G1"));

    	//공통코드 목록 조회 - G2:뉴스 분야
    	model.addAttribute("newsClCdList", commCodeAdmService.selectCommCodeList("G2"));

    	//뉴스 RSS URL 건수 조회
    	newsRssVo.setTotalRecordCount(newsRssService.selectNewsRssPageCount(newsRssVo));

		List<NewsRssUrlVo> newsRssList = new ArrayList<NewsRssUrlVo>();

		if (newsRssVo.getTotalRecordCount() > 0) {
			//뉴스 RSS URL 목록 조회
			newsRssList = newsRssService.selectNewsRssPageList(newsRssVo);
		}

		model.addAttribute("newsRssVo"  , newsRssVo);
		model.addAttribute("newsRssList", newsRssList);

        return "admin/news/main";
    }

    @ResponseBody
    @PostMapping("/admin/news/main/save")
    public HashMap<String, Object> save(NewsRssUrlVo newsRssVo) {

    	//뉴스 RSS 등록
    	newsRssService.insertNewsRss(newsRssVo);

        HashMap<String, Object> map = new HashMap<>();

        return map;
    }

}
