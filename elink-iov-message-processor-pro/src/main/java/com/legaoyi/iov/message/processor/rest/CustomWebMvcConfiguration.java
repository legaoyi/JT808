package com.legaoyi.iov.message.processor.rest;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("customWebMvcConfiguration")
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MultipartConfigElement multipartConfigElement;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 项目中的所有接口都支持跨域
                .allowedOrigins("*")// 所有地址都可以访问，也可以配置具体地址
                .allowCredentials(true).allowedMethods("*")// "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
                .maxAge(3600);// 跨域允许时间
    }

    /**
     * 1、 extends WebMvcConfigurationSupport 2、重写下面方法; setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配； setUseTrailingSlashMatch :
     * 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
        configurer.setUseTrailingSlashMatch(true);
    }

    @Bean("dispatcherRegistration")
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.setMultipartConfig(this.multipartConfigElement);
        // registration.addUrlMappings("*.do", "*.json");
        return registration;
    }
}
