package com.ccnuiot.xsystem01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

    @GetMapping("/page")
    public String getPage(){
        return "index";
    }
}
