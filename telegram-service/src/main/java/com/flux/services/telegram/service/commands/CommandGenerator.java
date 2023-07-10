package com.flux.services.telegram.service.commands;

import com.flux.services.telegram.service.TelegramUpdateService;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

public interface CommandGenerator {
    void generateCommand(Update update);

    String getInputCommand();

    @Autowired
    default void registerMySelf(TelegramUpdateService telegramUpdateService) {
        telegramUpdateService.register(getInputCommand(), this);
    }
}
