package com.example.springbootreactorbackend.event_driven.kafka_sample.CustomPropteriesConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app") // this will auto pic the application.propteries find inside app.name like grouped app.
@Data
public class CustomPropertiesConfig {
    private String name;              // Type-safe string, insted of using @Value()
    private String env;               // Type-safe string
    private double version;           // Type-safe double
    private boolean debug;            // Type-safe boolean
    private List<Integer> ports;      // Type-safe list of integers
    private Duration timeout;         // Type-safe time unit (if using YML with `10s`, etc.)
    private List<String> roles;

}
