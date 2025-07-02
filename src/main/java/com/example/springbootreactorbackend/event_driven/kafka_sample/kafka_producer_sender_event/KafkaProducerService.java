package com.example.springbootreactorbackend.event_driven.kafka_sample.kafka_producer_sender_event;

import com.example.springbootreactorbackend.event_driven.kafka_sample.DTO.MessagePayloadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService{
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, MessagePayloadModel payload) {
        logger.info("Sending message: {}",payload.getContent());
        kafkaTemplate.send(topic, payload.getId(), payload);
    }
}