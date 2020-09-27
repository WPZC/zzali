package zztest.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/28 14:35
 */
@Controller
@RequestMapping(value = "/test")
public class Test{

    @RequestMapping(value = "/te")
    @ResponseBody
    public String test(){

        String s = "";

        for(int i = 0;i<100;i++){
            s = s + i;
        }

        List<List<List<String>>> lists = new ArrayList<>();

        Gson gson = new Gson();

        gson.fromJson("",lists.getClass());

        return "这是test服务";





    }

}
