package com.flux.services.telegram.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flux.services.telegram.entity.UserVO;
import com.pengrad.telegrambot.model.Update;
import jakarta.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JmsService {

    @Value("${spring.activemq.topic}")
    String topic;

    private final JmsTemplate jmsTemplate;

    public JmsService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @SneakyThrows
    public void sendToTopic(Update update) {

        UserVO user = new UserVO(update.message().chat().id(), update.message().from().firstName(),update.message().from().lastName());
        String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(user);
        jmsTemplate.send(topic, messageCreator -> {
            TextMessage message = messageCreator.createTextMessage();
            message.setText(jsonObj);
            return message;
        });
        log.info("Sent message to topic \"{}\" with \"{}\" content.", topic, jsonObj);
    }
}
