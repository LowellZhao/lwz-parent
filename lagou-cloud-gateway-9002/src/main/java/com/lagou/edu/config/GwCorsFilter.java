package com.lagou.edu.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.annotation.Resource;

@Configuration
@ConditionalOnBean(GlobalCorsProperties.class)
public class GwCorsFilter {

    @Resource
    private GlobalCorsProperties globalCorsProperties;

    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        globalCorsProperties.getCorsConfigurations().forEach(source::registerCorsConfiguration);
        return new CorsWebFilter(source);
    }

}
