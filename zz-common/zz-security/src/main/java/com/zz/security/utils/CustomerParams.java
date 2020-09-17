package com.zz.security.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤参数
 * @author wqy
 * @version 1.0
 * @date 2020/9/17 14:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "customer")
public class CustomerParams {

    //get过滤，是否对get进行过滤
    private boolean get = true;
    //post过滤，是否对post进行过滤
    private boolean post = true;
    //要过滤的路由数组
    private String[] routes;

}
