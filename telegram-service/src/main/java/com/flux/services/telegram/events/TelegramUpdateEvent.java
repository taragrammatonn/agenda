package com.flux.services.telegram.events;

import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TelegramUpdateEvent extends ApplicationEvent {

    private final Update update;

    public TelegramUpdateEvent(Object source, Update update) {
        super(source);
        this.update = update;
    }
}
