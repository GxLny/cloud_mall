package com.lny.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 跨域配置类
 */
@Configuration
public class MallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        // 创建url注册跨域
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建跨域配置类
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 添加跨域配置信息
        //添加 允许哪些头跨域
        corsConfiguration.addAllowedHeader("*");
        //允许哪些请求方法跨域 * 所有
        corsConfiguration.addAllowedMethod("*");
        // 允许任意来源跨域
        corsConfiguration.addAllowedOrigin("*");
        // 是否允许携带cookie跨域
        corsConfiguration.setAllowCredentials(true);
        //注册配置到所有的路径中
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }


}
