package com.zz.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.function.Supplier;
import com.google.gson.Gson;
import com.zz.domain.ResultVO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 自定义异常处理
 * 其实就是把官方demo中的异常类复制过来，然后改改handle
 * @author wqy
 * @version 1.0
 * @date 2020/8/28 16:49
 */
public class SentinelBlocCustomerkExceptionHandler implements WebExceptionHandler {

    private List<ViewResolver> viewResolvers;
    private List<HttpMessageWriter<?>> messageWriters;
    private final Gson gson = new Gson();

    public SentinelBlocCustomerkExceptionHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.messageWriters = serverCodecConfigurer.getWriters();
    }

    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
        return response.writeTo(exchange, contextSupplier.get());
    }

    /**
     * 自定义异常处理
     * @param exchange
     * @param ex
     * @return
     */
    @NonNull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ex.printStackTrace();
        String error = ex.getMessage();
        ResultVO<String> rs = new ResultVO<>();
        rs.setCode(11);
        rs.setMsg(error);
        rs.setData(error);
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] datas = gson.toJson(rs).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
        return serverHttpResponse.writeWith(Mono.just(buffer));

    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }

    private final Supplier<ServerResponse.Context> contextSupplier = () -> new ServerResponse.Context() {
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return SentinelBlocCustomerkExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return SentinelBlocCustomerkExceptionHandler.this.viewResolvers;
        }
    };

}
