package com.zz.zzsystemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/26 17:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ZzApiAuthorityApplicatoin {

    public static void main(String[] args) {
        SpringApplication.run(ZzApiAuthorityApplicatoin.class, args);
    }

}
