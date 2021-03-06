package com.cn.weixineureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WeixinEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinEurekaApplication.class, args);
    }

}
