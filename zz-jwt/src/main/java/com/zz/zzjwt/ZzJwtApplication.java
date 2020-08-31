package com.zz.zzjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.zz.security.annotation.EnableZzFeignClients;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/26 16:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZzFeignClients
public class ZzJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzJwtApplication.class,args);
    }

}
