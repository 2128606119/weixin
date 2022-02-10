package com.cn.weixinuser;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
//fadfs文件服务器整合
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)

@EnableEurekaClient
@MapperScan(basePackages = "com.cn.weixinuser.mapper")
@SpringBootApplication
@EnableCircuitBreaker
public class WeixinUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeixinUserApplication.class, args);
    }
}
