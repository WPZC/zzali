package com.zz.zzlogserver;

import com.zz.security.annotation.EnableZzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/29 14:23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZzFeignClients
public class ZzLogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzLogServerApplication.class,args);
    }

}
