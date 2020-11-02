package com.lagou.edu.filiter;

import com.lagou.edu.cloud.service.UserServiceFeignClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private UserServiceFeignClient userServiceFeignClient;

    /**
     * 过滤器核心方法
     *
     * @param exchange 封装了request和response对象的上下文
     * @param chain    网关过滤器链（包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        HttpCookie cookie = cookies.getFirst("token");
        if (cookie == null) {
            return chain.filter(exchange);
        } else {
            String token = cookie.getValue();
            String email = userServiceFeignClient.info(token);
            if (email == null || "".equals(email)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().set(HttpHeaders.LOCATION, "http://www.test.com/static/login.html");
                return response.setComplete();
            } else {
                return chain.filter(exchange);
            }
        }
    }


    /**
     * 返回值表示当前过滤器的顺序(优先级)，数值越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }

}
