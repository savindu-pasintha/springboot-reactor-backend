package com.example.springbootreactorbackend.event_driven.kafka_sample.CustomPropteriesConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaTopicGroupProperties {
    private Map<String, String> topic;
    private Map<String, String> group;
}
