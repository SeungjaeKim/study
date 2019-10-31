package com.study.admin.comm.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.admin.comm.domain.CommCodeVo;
import com.study.admin.comm.service.CommCodeAdmService;
import com.study.common.StringUtils;

/**
 * 공통코드
 */
@Controller
public class CommCodeAdmController {

    @Autowired
    private CommCodeAdmService commCodeAdmService;

    @ResponseBody
    @RequestMapping("/comm/getCommCodeList")
    public HashMap<String, Object> getCommCodeList(CommCodeVo commCodeVo) {

    	HashMap<String, Object> map = new HashMap<>();

    	if (StringUtils.isNotBlank(commCodeVo.getCodeGroupId())) {

    		//공통코드 목록 조회
    		map.put("commCodeList", commCodeAdmService.selectCommCodeList(commCodeVo));
    	}

        return map;
    }

}
