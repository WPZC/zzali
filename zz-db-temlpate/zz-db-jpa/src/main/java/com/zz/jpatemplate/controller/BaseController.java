package com.zz.jpatemplate.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Backtrack;
import com.zz.domain.BaseDoMain;
import com.zz.domain.PageData;
import com.zz.domain.PageInfo;
import com.zz.domain.ResultVO;
import com.zz.jpatemplate.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/27 9:15
 */
@Controller
public class BaseController<T extends BaseDoMain,B extends BaseService>{

    //service
    @Autowired
    private B b;

    @ApiOperation(value = "分页获取列表",notes = "参数JSON:{\"currentPage(页码)\":1,\"pagesize(一页显示条数)\":10,\"sort(排序类型，ASC正序，DESC倒序)\":\"DESC\",\"filed(字段)\":\"id\",\"paramInfos(参数集)\":[{\"field(参数字段)\":\"testing\",\"params(对应字段的参数)\":[\"2020-09-23\",\"2020-09-23\"],\"symbol(符号)\":\"BETWEEN\"}]}")
    @RequestMapping(value = "/getPageData",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<PageData<T>> getPageData(String pageInfo) throws Exception {

        PageInfo p = new PageInfo();

        p = JSONObject.parseObject(pageInfo,p.getClass());

        return Backtrack.success(b.findByPages(p));

    }

}
