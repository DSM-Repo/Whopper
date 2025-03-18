package com.dsm.repo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableFeignClients
@EnableMethodSecurity
@SpringBootApplication
@ConfigurationPropertiesScan
@ImportAutoConfiguration({FeignAutoConfiguration.class})
class   Application {

    public static void main(final String[] args) {
        SpringApplication.run(
                Application.class,
                args
        );
    }

}
