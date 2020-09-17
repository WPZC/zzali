package com.zz.zzbaseapi;

import com.zz.security.annotation.EnableZzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/9 11:05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZzFeignClients
public class ZzBaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzBaseApiApplication.class,args);
    }

}
