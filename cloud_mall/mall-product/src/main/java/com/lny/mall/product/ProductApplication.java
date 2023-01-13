package com.lny.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 开启远程调用服务支持
 *
 * 1. 页面修改不重启服务器事实更新
 *     1）引入devtools
 *     2）shift f9 重新编译页面
 *
 */
@EnableFeignClients(basePackages = "com.lny.mall.product.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}

