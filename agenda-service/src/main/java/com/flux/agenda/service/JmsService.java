package com.flux.agenda.service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JmsService {

    @JmsListener(destination = "topic")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData;
        log.info("Received message in 2nd topic {}", jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("messageData in 2nd listener: {}", messageData);
        }
    }
}
