package zztest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/28 14:35
 */
@Controller
@RequestMapping(value = "/test")
public class Test {

    @RequestMapping(value = "/te")
    @ResponseBody
    public String test(){

        return "这是test服务";

    }

}
