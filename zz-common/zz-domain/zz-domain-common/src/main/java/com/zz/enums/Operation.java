package com.zz.enums;

/**
 * 运算符
 * @author wqy
 * @version 1.0
 * @date 2020/11/9 11:43
 */
public enum Operation {

    //正序
    AND(1,"且"),
    //倒叙
    OR(2,"或");

    //下标
    private Integer index;
    //描述
    private String description;

    Operation(Integer index, String description){
        this.index = index;
        this.description = description;
    }

}
