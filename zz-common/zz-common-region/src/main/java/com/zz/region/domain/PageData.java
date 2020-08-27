package com.zz.region.domain;

import lombok.Data;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 9:26
 */
@Data
public class PageData<T> {

    //总数
    private Long total;
    //页数
    private Integer pagesize;
    //数据
    private List<T> listData;
}
