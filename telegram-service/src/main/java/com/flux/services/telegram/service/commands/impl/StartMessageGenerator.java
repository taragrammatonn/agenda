package com.flux.services.telegram.service.commands.impl;

import com.flux.services.telegram.client.ClientEvents;
import com.flux.services.telegram.service.commands.CommandGenerator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartMessageGenerator implements CommandGenerator {

    private final TelegramBot bot;

    final ClientEvents clientEvents;

    public StartMessageGenerator(TelegramBot bot, ClientEvents clientEvents) {
        this.bot = bot;
        this.clientEvents = clientEvents;
    }

    @Override
    public void generateCommand(Update update) {
        List<?> allEvents = clientEvents.getAllEvents();
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), allEvents.toString());
        sendMessage.replyMarkup(setStickyButtons());
        bot.execute(sendMessage);
    }

    @Override
    public String getInputCommand() {
        return "/start";
    }

    public ReplyKeyboardMarkup setStickyButtons() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup("My calendar");
        replyKeyboardMarkup.selective(true);
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}