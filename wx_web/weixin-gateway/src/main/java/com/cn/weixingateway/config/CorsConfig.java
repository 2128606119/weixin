package com.cn.weixingateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig {

    //添加跨域过滤器
    @Bean
    public CorsWebFilter corsWebFilter() {
        // 基于url跨域
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 跨域配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);

        List<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("*");
        corsConfiguration.setAllowedOriginPatterns(allowedOriginPatterns);

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");

        // 向source中注册new出来的配置，并设置任意url都要进行跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}