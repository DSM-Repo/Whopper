package com.example.whopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WhopperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhopperApplication.class, args);
    }

}
