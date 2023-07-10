package com.flux.services.telegram;

import com.pengrad.telegrambot.TelegramBot;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Telegram API", version = "1.0", description = "Telegram API v1.0")
)
public class TelegramServiceApplication {

    @Value("${bot.token}")
    private String token;

    private static String TOKEN_STATIC;

    @Value("${bot.token}")
    public void setNameStatic(String name){
        TelegramServiceApplication.TOKEN_STATIC = name;
    }


    public static void main(String[] args) {
        SpringApplication.run(TelegramServiceApplication.class, args);

        TelegramBot bot = new TelegramBot.Builder(TOKEN_STATIC).build();

//        bot.setUpdatesListener(updates -> {
//            // ... process updates
//            // return id of last processed update or confirm them all
//            log.info(updates.toString());
//            return UpdatesListener.CONFIRMED_UPDATES_ALL;
//// Create Exception Handler
//        }, e -> {
//            if (e.response() != null) {
//                // got bad response from telegram
//                e.response().errorCode();
//                e.response().description();
//            } else {
//                // probably network error
//                e.printStackTrace();
//            }
//        });
//    GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

//        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
//            @Override
//            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
//                List<Update> updates = response.updates();
//            }
//
//            @Override
//            public void onFailure(GetUpdates request, IOException e) {
//
//            }
//        });

        // Create Exception Handler

    }

}
