package com.cn.weixingateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class WeixinGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinGatewayApplication.class, args);
    }

}
