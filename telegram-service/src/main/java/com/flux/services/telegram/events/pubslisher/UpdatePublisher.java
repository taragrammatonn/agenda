package com.flux.services.telegram.events.pubslisher;

import com.flux.services.telegram.events.TelegramUpdateEvent;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UpdatePublisher {

    private final ApplicationEventPublisher publisher;

    public UpdatePublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishEvent(final List<Update> update) {
        log.info("Publish event from: {}", getClass());
        // Publishing event created by extending ApplicationEvent
        update.forEach(it -> publisher.publishEvent(new TelegramUpdateEvent(this, it)));
    }
}
