package com.zz.zzmqkafkatest.controller;

import com.zz.KafkaCustomer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * 类功能描述：<br>
 * <ul>
 * <li>类功能描述1<br>
 * <li>类功能描述2<br>
 * <li>类功能描述3<br>
 * </ul>
 * 修改记录：<br>
 * <ul>
 * <li>修改记录描述1<br>
 * <li>修改记录描述2<br>
 * <li>修改记录描述3<br>
 * </ul>
 *
 * @author xuefl
 * @version 5.0 since 2020-01-13
 */
@Component
@Slf4j
public class KafkaConsumer extends KafkaCustomer {
    @Override
    @KafkaListener(topics = "topic.test", groupId = "topic.group1")
    public void topicCustomer(ConsumerRecord<?, ?> record, Acknowledgment ack, String topic) {
        {
            Optional message = Optional.ofNullable(record.value());
            if (message.isPresent()) {
                Object msg = message.get();
                log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
                ack.acknowledge();
            }
        }
    }
    @KafkaListener(topics = "topic.test", groupId = "topic.group1")
    public void topic_test2(ConsumerRecord<?, ?> record, Acknowledgment ack, String topic) {
        {
            Optional message = Optional.ofNullable(record.value());
            if (message.isPresent()) {
                Object msg = message.get();
                log.info("topic_test22222 消费了： Topic:" + topic + ",Message:" + msg);
                ack.acknowledge();
            }
        }
    }
}