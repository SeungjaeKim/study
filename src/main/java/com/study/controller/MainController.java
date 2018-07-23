package com.study.controller;

import org.springframework.stereotype.Controller;
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



}
