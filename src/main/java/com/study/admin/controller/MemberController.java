package com.study.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 어드민 사용자
 */
@Controller
public class MemberController {
	
    @GetMapping("/admin/member/login")
    public String main(Model model) {
    	
        return "admin/member/login";
    }

}
