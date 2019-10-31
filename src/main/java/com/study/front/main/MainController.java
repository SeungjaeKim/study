package com.study.front.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by seungjae 2018-07-23
 */
@Controller
public class MainController {

    @GetMapping("/main")
    public String Main(){
        return "index";
    }

    @GetMapping("/test")
    public String Test(Model model){
        String name = "tester";

        model.addAttribute("name", name);

        return "test";
    }



}
