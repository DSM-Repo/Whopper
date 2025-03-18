package com.repo.whopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@ImportAutoConfiguration({FeignAutoConfiguration.class})
class Application {

    public static void main(final String[] args) {
        SpringApplication.run(
                Application.class,
                args
        );
    }

}
