package com.example.springbootreactorbackend.event_driven.kafka_sample.kafka_consumer_lister_event;
import com.example.springbootreactorbackend.event_driven.kafka_sample.CustomPropteriesConfig.KafkaTopicGroupProperties;
import com.example.springbootreactorbackend.event_driven.kafka_sample.DTO.MessagePayloadModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    @Autowired
    private  KafkaTopicGroupProperties kafkaTopicGroupProperties;

    @KafkaListener(
            topics = "#{@kafkaTopicGroupProperties.topic['order']}",
            groupId = "#{@kafkaTopicGroupProperties.group['order']}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeOrder(MessagePayloadModel payload, ConsumerRecord<String, MessagePayloadModel> record) {
        logger.info("Order Consumer Received: {} from partition: {}", payload, record.partition());
        if (payload.getContent().contains("error")) {
            throw new RuntimeException("Order processing error");
        }
    }
    @KafkaListener(topics = "prod-topic",
            groupId = "prod-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(MessagePayloadModel payload, ConsumerRecord<String, MessagePayloadModel> record) {
        logger.info("Received: {}", payload, record);
        if (payload.getContent().contains("error")) {
            throw new RuntimeException("Simulated processing error");
        }
    }
    @KafkaListener(topics = "user-topic", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeUser(MessagePayloadModel payload, ConsumerRecord<String, MessagePayloadModel> record) {
        logger.info("User Consumer Received: {} from partition: {}", payload, record.partition());
        if (payload.getContent().contains("error")) {
            throw new RuntimeException("User processing error");
        }
    }
    @KafkaListener(topics = "payment-topic", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumePayment(MessagePayloadModel payload, ConsumerRecord<String, MessagePayloadModel> record) {
        logger.info("Payment Consumer Received: {} from partition: {}", payload, record.partition());
        if (payload.getContent().contains("error")) {
            throw new RuntimeException("Payment processing error");
        }
    }

    @KafkaListener(topics = "cart-topic", groupId = "cart-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeCart(MessagePayloadModel payload, ConsumerRecord<String, MessagePayloadModel> record) {
        logger.info("Cart Consumer Received: {} from partition: {}", payload, record.partition());
        if (payload.getContent().contains("error")) {
            throw new RuntimeException("Cart processing error");
        }
    }
}
