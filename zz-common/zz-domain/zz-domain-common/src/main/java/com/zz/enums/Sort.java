package com.zz.enums;

/**
 * 排序
 * @author wqy
 * @version 1.0
 * @date 2020/9/27 9:50
 */
public enum Sort {

    //正序
    ASC(1,"正序"),
    //倒叙
    DESC(2,"倒序");

    //下标
    private Integer index;
    //描述
    private String description;

    Sort(Integer index,String description){
        this.index = index;
        this.description = description;
    }

}
