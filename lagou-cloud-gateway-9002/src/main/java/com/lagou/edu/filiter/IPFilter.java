package com.lagou.edu.filiter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IPFilter implements GlobalFilter, Ordered {

    private static Map<String, List<LocalDateTime>> ipMap = new ConcurrentHashMap<>();

    /**
     * 过滤器核心方法
     *
     * @param exchange 封装了request和response对象的上下文
     * @param chain    网关过滤器链（包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        RequestPath path = request.getPath();
        System.out.println(path);
        if (path.toString().startsWith("/user/register/")) {
            String host = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
            LocalDateTime now = LocalDateTime.now();
            if (ipMap.get(host) == null) {
                List<LocalDateTime> list = new ArrayList<>();
                list.add(now);
                ipMap.put(host, list);
            } else {
                ipMap.get(host).add(now);
            }
            LocalDateTime oneMinBefore = now.minusMinutes(1);
            for (int i = 0; i < ipMap.get(host).size(); i++) {
                if (ipMap.get(host).get(i).isBefore(oneMinBefore)) {
                    ipMap.get(host).remove(ipMap.get(host).get(i));
                }
            }
            int count = ipMap.get(host).size();
            if (count > 10) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                String data = "当前IP在一分钟内注册超过10个，请稍后再试.";
                DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                return response.writeWith(Mono.just(wrap));
            } else {
                return chain.filter(exchange);
            }
        } else {
            return chain.filter(exchange);
        }
    }

    /**
     * 返回值表示当前过滤器的顺序(优先级)，数值越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
