package com.team3.gdgoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GdgocApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdgocApplication.class, args);
    }

}
