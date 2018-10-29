package com.study.news.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.news.domain.CommCodeVo;
import com.study.news.service.CommCodeService;

/**
 * 공통코드
 */
@Controller
public class CommCodeController {
	
    @Autowired
    private CommCodeService commCodeService;
	
    @ResponseBody
    @PostMapping("/comm/getCommCodeList")
    public Model getCommCodeList(Model model, CommCodeVo commCodeVo) {

    	if (StringUtils.isNotBlank(commCodeVo.getCodeGroupId())) {
    		
    		//공통코드 목록 조회
    		model.addAttribute("commCodeList", commCodeService.selectCommCodeList(commCodeVo));
    	}
    	
        return model;
    } 

}
