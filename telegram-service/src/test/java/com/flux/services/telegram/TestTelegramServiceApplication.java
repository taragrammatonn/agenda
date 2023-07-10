package com.flux.services.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTelegramServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(TelegramServiceApplication::main).with(TestTelegramServiceApplication.class).run(args);
    }

}
