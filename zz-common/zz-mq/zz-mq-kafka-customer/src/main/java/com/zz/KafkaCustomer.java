package com.zz;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * kafka消费者
 * @author wqy
 * @version 1.0
 * @date 2020/9/28 15:54
 */
@Slf4j
public abstract class KafkaCustomer {

    //@KafkaListener(topics = TOPIC_TEST, groupId = TOPIC_GROUP1)
    public abstract void topicCustomer(ConsumerRecord<?, ?> record, Acknowledgment ack,String topic);

}
