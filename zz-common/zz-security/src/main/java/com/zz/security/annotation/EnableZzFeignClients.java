package com.zz.security.annotation;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * 自定义feign注解
 * 添加basePackages路径
 * 
 * @author wqy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityScan(basePackages={"com.zz.domain.*"})
@EnableFeignClients
public @interface EnableZzFeignClients
{
    String[] value() default {};

    String[] basePackages() default { "com.zz" };

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
