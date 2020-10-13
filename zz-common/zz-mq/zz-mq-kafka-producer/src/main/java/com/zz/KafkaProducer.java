package com.zz;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import com.zz.vo.SendData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/28 15:21
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void  sendMsg(SendData<T> sendData) {
        Assert.notNull(sendData.getTopic(),"topic不能为空！！！！");

        if(ObjectUtils.isEmpty(sendData.getData())){
            log.warn("SendData中数据为空");
        }else {
            log.info(sendData.getTopic()+"发送数据"+JSONObject.toJSONString(sendData.getData()));
        }

        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(sendData.getTopic(), JSONObject.toJSONString(sendData.getData()));
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(sendData.getTopic() + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(sendData.getTopic() + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });


    }



}
