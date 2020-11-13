package com.zz.zzspringtest.test;

import com.zz.zzspringtest.inf.PersionImpl;
import com.zz.zzspringtest.manage.PersionManage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/3 17:35
 */
public class PersionTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.zz.zzspringtest");

        Stream.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);

        PersionManage manage = ctx.getBean(PersionManage.class);

        System.out.println(manage.persionEat(new PersionImpl()));
    }

}
