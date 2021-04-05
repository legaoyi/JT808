package com.legaoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@SpringBootApplication
@EnableCaching
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

    @Bean
    public AppApplicationListener appApplicationListener() {
        return new AppApplicationListener();
    }
}
