package com.ljy.oneclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectHandler {
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url, HttpSession session){
        if (session.getAttribute("userInfo")==null)
            return "login";
        return url;
    }

    @GetMapping("adminpage/{urlb}")
    public String redirectM(@PathVariable("urlb") String urlb){
        return "adminpage/"+urlb;
    }
    @GetMapping("adminpage/{urla}/{urlb}")
    public String redictAPage(@PathVariable("urla") String urla,@PathVariable("urlb") String urlb){
        return "adminpage/"+urla+"/"+urlb;
    }

    @GetMapping("clubpage/{urlb}")
    public String redirectclubM(@PathVariable("urlb") String urlb){
        return "clubpage/"+urlb;
    }
    @GetMapping("clubpage/{urla}/{urlb}")
    public String redictclubAPage(@PathVariable("urla") String urla,@PathVariable("urlb") String urlb){
        return "clubpage/"+urla+"/"+urlb;
    }

    @RequestMapping("admin/{urlb}/{urlc}")
    public String redirectT(@PathVariable("urlb") String urlb,@PathVariable("urlc") String urlc){
        return "admin/"+urlb+"/"+urlc;
    }
}
