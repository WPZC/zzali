package com.zz.zzjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WQY
 * @Date 2019/11/26 10:51
 * @Version 1.0
 */
@Controller
@RequestMapping
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "/hello.html";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "/login.html";
    }

    @RequestMapping("/index")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index() {
        return "此处只能是ROLE_ADMIN访问";
    }

    @RequestMapping("/test2")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('ROLE_ADMIN2')")
    public String test2() {
        return "此处只能是ROLE_ADMIN访问";
    }

}
