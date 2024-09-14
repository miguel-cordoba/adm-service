package com.miguelcordoba.admservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "authors-topic";

    @Autowired
    public AuthorProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
