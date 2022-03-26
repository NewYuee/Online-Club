package com.ljy.oneclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectHandler {
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url, HttpSession session){
        if (session.getAttribute("userInfo")==null)
            return "login";
        return url;
    }
}
