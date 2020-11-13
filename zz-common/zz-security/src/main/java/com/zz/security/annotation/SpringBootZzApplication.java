package com.zz.security.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.*;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/4 17:51
 */
//配置扫描路径
@SpringBootApplication(
        scanBasePackages = {
                "com.zz.*.controller",
                "com.zz.*.config",
                "com.zz.*.service",
                "com.zz.*.bean"
        })
@EnableDiscoveryClient
@EnableZzFeignClients
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringBootZzApplication {

}

