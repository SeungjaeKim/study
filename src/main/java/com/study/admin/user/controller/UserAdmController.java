package com.study.admin.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 어드민 사용자
 */
@Controller
public class UserAdmController {
	
    /**
     * 구글 API 클라이언트 ID
     */
    @Value("${gapi.client.id}")
    private String gapiClientId;
	
    @RequestMapping("/admin/user/loginForm")
    public String loginForm(Model model) {
    	
    	model.addAttribute("gapiClientId", gapiClientId);
    	
        return "admin/user/loginForm";
    }

}
