package com.zz.aop.topic;

/**
 * topic规则
 * @author wqy
 * @version 1.0
 * @date 2020/10/20 14:16
 */
public enum TopicType {

    /**
     * 用户日志
     */
    TOPIC_USER(1,"topic.user");

    //下标
    public Integer index;
    //描述
    public String description;

    TopicType(Integer index,String description){
        this.index = index;
        this.description = description;
    }
}
