package com.zz.zzlogserver.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zz.KafkaCustomer;
import com.zz.zzlogserver.domain.LogInfo;
import com.zz.zzlogserver.jpa.LogInfoJpa;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费者，改类监听topic.user
 * @author wqy
 * @version 1.0
 * @date 2020/9/29 14:33
 */
@Component
@Slf4j
public class UserCustomer extends KafkaCustomer {

    @Autowired
    private LogInfoJpa logInfoJpa;

    @Override
    @KafkaListener(topics = "topic.user",groupId = "group.sysoperation")
    public void topicCustomer(ConsumerRecord<?, ?> record, Acknowledgment ack, String topic) {

        System.out.println(record.value());

        LogInfo logInfo = new LogInfo();

        logInfo = JSON.parseObject(record.value().toString(),logInfo.getClass());

        //logInfo.setOperationTime(new Date());

        logInfoJpa.save(logInfo);
        //确认消费
        ack.acknowledge();

    }
}
