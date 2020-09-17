package com.zz.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 9:26
 */
@Data
@ApiModel(value = "分页数据")
public class PageData<T> {

    //总数
    @ApiModelProperty(value = "总数",name = "total",dataType = "Long")
    private Long total;
    //页数
    @ApiModelProperty(value = "页数",name = "pagesize",dataType = "Integer")
    private Integer pagesize;
    //数据
    @ApiModelProperty(value = "数据列表",name = "listData",dataType = "List")
    private List<T> listData;
}
