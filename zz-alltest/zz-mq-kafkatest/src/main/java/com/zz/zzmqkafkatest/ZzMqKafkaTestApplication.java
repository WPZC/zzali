package com.zz.zzmqkafkatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/28 11:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ZzMqKafkaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzMqKafkaTestApplication.class,args);
    }

}
