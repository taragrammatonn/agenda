package com.flux.services.telegram.service;

import com.flux.services.telegram.service.commands.CommandGenerator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TelegramUpdateService {

    protected final Map<String, CommandGenerator> commands = new HashMap<>();

    private final TelegramBot bot;

    public TelegramUpdateService(TelegramBot bot) {
        this.bot = bot;
    }

    public void messageProcessing(Update update) {
        if (commands.containsKey(update.message().text()))
            commands.get(update.message().text()).generateCommand(update);
        else sendDefaultResponse(update);

    }

    private void sendDefaultResponse(Update update) {
        SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Invalid command."));
        log.debug("Send response is ok: {}", sendResponse.isOk());
        log.debug("Send response message: {}", sendResponse.message());
    }

    public void register(String inputCommand, CommandGenerator commandGenerator) {
        commands.put(inputCommand, commandGenerator);
    }
}
