package com.study.news.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.common.StringUtils;
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
    @RequestMapping("/comm/getCommCodeList")
    public HashMap<String, Object> getCommCodeList(CommCodeVo commCodeVo) {

    	HashMap<String, Object> map = new HashMap<>();

    	if (StringUtils.isNotBlank(commCodeVo.getCodeGroupId())) {

    		//공통코드 목록 조회
    		map.put("commCodeList", commCodeService.selectCommCodeList(commCodeVo));
    	}

        return map;
    }

}
