package com.zz.gateway.filter;

import lombok.extern.slf4j.Slf4j;
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
 * 拦截过滤
 * @author wqy
 * @version 1.0
 * @date 2020/8/1 15:23
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("********come in MyLogGatewayFilter：" + new Date());
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getPath());
        System.out.println(request.getQueryParams());
        System.out.println(request.getHeaders().get("token"));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
