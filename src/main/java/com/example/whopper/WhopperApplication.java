package com.example.whopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class WhopperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhopperApplication.class, args);
    }

}
