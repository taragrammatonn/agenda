package com.flux.services.telegram.controller;

import com.flux.services.telegram.events.TelegramUpdateEvent;
import com.flux.services.telegram.service.TelegramUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgendaBot {

    private final TelegramUpdateService telegramUpdateService;

    public AgendaBot(TelegramUpdateService telegramUpdateService) {
        this.telegramUpdateService = telegramUpdateService;
    }

    @EventListener
    public void handleTelegramUpdateEvent(TelegramUpdateEvent event) {
        // handle UserRemovedEvent ...
        log.debug("Received TelegramUpdateEvent. Event: {}", event.toString());
        telegramUpdateService.messageProcessing(event.getUpdate());
    }
}
