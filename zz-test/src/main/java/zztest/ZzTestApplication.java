package zztest;

import com.zz.security.annotation.EnableZzFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/26 15:38
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZzFeignClients
public class ZzTestApplication {

    public static void main(String[] args){
        SpringApplication.run(ZzTestApplication.class,args);
    }
}
