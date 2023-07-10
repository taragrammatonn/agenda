package com.flux.services.telegram.aop;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class UpdateAspect {

    private final TelegramBot bot;

    public UpdateAspect(TelegramBot bot) {
        this.bot = bot;
    }

    @SneakyThrows
    @Around("Pointcuts.allReceivedMessages()")
    public Object aroundAddingTypeChatAction(ProceedingJoinPoint joinPoint) {
        Update update = (Update) Arrays.stream(joinPoint.getArgs())
                .filter(Update.class::isInstance)
                .findFirst()
                .orElse(null);

        if (update != null)
            bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing));

        return joinPoint.proceed();
    }
}
