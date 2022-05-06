package com.datealive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GzhuteamApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzhuteamApplication.class, args);
    }

}
