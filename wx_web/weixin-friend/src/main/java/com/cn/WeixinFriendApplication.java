package com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan(basePackages = "com.cn.mapper")
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class WeixinFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinFriendApplication.class, args);
    }

}
