package com.study.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public String main(Model model, NewsRssVo newsRssVo) {
    	
    	//공통코드 목록 조회 - G1:뉴스 언론사
    	model.addAttribute("newsCompCdList", commCodeService.selectCommCodeList("G1"));
    	
    	//공통코드 목록 조회 - G2:뉴스 분야
    	model.addAttribute("newsClCdList", commCodeService.selectCommCodeList("G2"));
    	
//		PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(1);        //현재 페이지 번호
//		paginationInfo.setRecordCountPerPage(50);  //한 페이지에 게시되는 게시물 건수
//		paginationInfo.setPageSize(10);            //페이징 리스트의 사이즈
//		paginationInfo.setTotalRecordCount(newsRssService.selectNewsRssPageCount(newsRssVo));  //전체 게시물 건 수
    	
    	newsRssVo.setTotalRecordCount(newsRssService.selectNewsRssPageCount(newsRssVo));  //전체 게시물 건 수
		
		List<NewsRssVo> newsRssList = new ArrayList<NewsRssVo>(); 
		
		if (newsRssVo.getTotalRecordCount()>0) {
			newsRssList = newsRssService.selectNewsRssPageList(newsRssVo);
		}
		
		model.addAttribute("newsRssVo"     , newsRssVo);
		model.addAttribute("newsRssList"   , newsRssList);
//		model.addAttribute("paginationInfo", paginationInfo);
    	
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
