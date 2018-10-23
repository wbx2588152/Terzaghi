package com.jk;

import com.jk.shiro.FebsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigurationProperties({FebsProperties.class})
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCaching
@EnableAsync
public class SpringcloudFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudFeignApplication.class, args);
    }
}
