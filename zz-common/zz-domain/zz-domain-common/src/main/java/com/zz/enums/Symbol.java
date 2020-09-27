package com.zz.enums;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/27 10:40
 */
public enum Symbol {

    //全等
    EQ(1,"全等"),
    //不等
    NQ(2,"不等"),
    //模糊查询
    LIKE(3,"模糊查询"),
    //非模糊查询
    NOTLIKE(4,"非模糊查询"),
    //在两者之间
    BETWEEN(5,"在两者之间"),
    //小于等于
    LE(6,"小于等于"),
    //大于等于
    GE(7,"大于等于");

    //下标
    private Integer index;
    //描述
    private String description;

    Symbol(Integer index,String description){
        this.index = index;
        this.description = description;
    }

}
