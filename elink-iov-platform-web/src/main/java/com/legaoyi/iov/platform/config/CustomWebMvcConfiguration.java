package com.legaoyi.iov.platform.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 高胜波
 */
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MultipartConfigElement multipartConfigElement;

    /**
     * 1、 extends WebMvcConfigurationSupport 2、重写下面方法; setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配； setUseTrailingSlashMatch :
     * 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
        configurer.setUseTrailingSlashMatch(true);
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.setMultipartConfig(this.multipartConfigElement);
        registration.addUrlMappings("","*.do");
        return registration;
    }
}
