package com.flux.services.telegram.service.commands.impl;

import com.flux.services.telegram.service.JmsService;
import com.flux.services.telegram.service.commands.CommandGenerator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartMessageGenerator implements CommandGenerator {

    private final TelegramBot bot;
    private final JmsService jmsService;

    public StartMessageGenerator(TelegramBot bot, JmsService jmsService) {
        this.bot = bot;
        this.jmsService = jmsService;
    }

    @Override
    public void generateCommand(Update update) {
        jmsService.sendToTopic(update);
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), "response");
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