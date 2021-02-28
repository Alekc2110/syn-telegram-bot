package com.example.mysynergybot;

import org.hibernate.annotations.SQLInsert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication
public class MysynergybotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(MysynergybotApplication.class, args);
    }

}
