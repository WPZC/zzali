package com.zz.gateway.filter;

import com.zz.gateway.feign.SecuritypePermissionFeign;
import com.zz.region.methods.Backtrack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/1 15:23
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Autowired
    private SecuritypePermissionFeign securitypePermissionFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("********come in MyLogGatewayFilter：" + new Date());
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getPath());
        System.out.println(request.getQueryParams());
        System.out.println(request.getHeaders().get("token"));
        HttpHeaders headers = request.getHeaders();
        List<String> strs = headers.get("token");
        //获取token并验证
        String token = strs.get(0);
        System.out.println(token);
        Boolean check = Backtrack.checkData(securitypePermissionFeign.verificationToken(token));
        System.out.println(check);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
