package com.zz.zzjwt.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WQY
 * @Date 2019/11/26 10:51
 * @Version 1.0
 */
@Api(tags = "hello")
@Controller
@RequestMapping
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "/hello.html";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "/login.html";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index() {
        return "此处只能是ROLE_ADMIN访问";
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('ROLE_ADMIN2')")
    public String test2() {
        return "此处只能是ROLE_ADMIN访问";
    }

}
