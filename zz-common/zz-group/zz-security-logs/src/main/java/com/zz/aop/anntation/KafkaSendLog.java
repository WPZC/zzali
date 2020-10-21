package com.zz.aop.anntation;

import com.zz.aop.topic.TopicType;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author wqy
 * @version 1.0
 * @date 2020/10/20 10:45
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KafkaSendLog {

    TopicType topic();

}
