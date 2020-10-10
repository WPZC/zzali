package com.zz.vo;

import lombok.Data;

/**
 * kafka发送数据对象
 * @author wqy
 * @version 1.0
 * @date 2020/9/28 15:33
 */
@Data
public class SendData<T> {

    /**
     * topic,对应消费者监听的
     */
    private String topic;

    /**
     * 消息提
     */
    private T data;

    public SendData(String topic,T data){
        this.topic = topic;
        this.data = data;
    }
}
