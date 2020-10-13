package com.zz.zzswrapp;

import com.zz.security.annotation.EnableZzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/9 11:05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZzFeignClients
public class ZzSwrAppApplicatoin {

    public static void main(String[] args) {
        SpringApplication.run(ZzSwrAppApplicatoin.class,args);
    }

}
