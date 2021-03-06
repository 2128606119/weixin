package com.cn.weixinconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class WeixinConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinConfigServerApplication.class, args);
    }

}
