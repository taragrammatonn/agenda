package com.flux.services.telegram.configuration;

import com.flux.services.telegram.events.pubslisher.UpdatePublisher;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Configuration {

    @Value("${bot.token}")
    private String token;

    @Bean
    public TelegramBot telegramBot(UpdatePublisher updatePublisher) {
        TelegramBot telegramBot = new TelegramBot.Builder(token).build();
        telegramBot.setUpdatesListener(updates -> {
            // publish updates
            updatePublisher.publishEvent(updates);

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                // got bad response from telegram
                log.error("Bad response from Telegram: {}\n. The error code is: {}",e.response().description(), e.response().errorCode());
            } else {
                // probably network error
                log.error("UpdatePublisher Exception!", e);
            }
        });
        return telegramBot;
    }
}
