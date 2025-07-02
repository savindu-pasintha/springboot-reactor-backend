package com.example.springbootreactorbackend.web.mcv.rest_sample.controllers;

import com.example.springbootreactorbackend.event_driven.kafka_sample.CustomPropteriesConfig.KafkaTopicGroupProperties;
import com.example.springbootreactorbackend.event_driven.kafka_sample.DTO.MessagePayloadModel;
import com.example.springbootreactorbackend.event_driven.kafka_sample.kafka_producer_sender_event.KafkaProducerService;
import com.example.springbootreactorbackend.utilities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestControllers {
    @Autowired
    Log log;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaTopicGroupProperties kafkaTopicGroupProperties;
    @GetMapping("/kafka")
    public ResponseEntity<String> getKafkaProducer() {

        MessagePayloadModel messagePayloadModel = new MessagePayloadModel("1","New Kafka MSg from /kafka");
        kafkaProducerService.sendMessage("prod-topic",messagePayloadModel);
        kafkaProducerService.sendMessage(kafkaTopicGroupProperties.getTopic().get("order"),messagePayloadModel);
        return ResponseEntity.ok("Kafka: ");
    }
    // 1. GET with path variable
    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUserById(@PathVariable String id) {
        return ResponseEntity.ok("User ID: " + id);
    }

    // 2. GET with query parameters
    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String keyword,
                                         @RequestParam(required = false, defaultValue = "10") int limit) {
        return ResponseEntity.ok("Searching for: " + keyword + " | Limit: " + limit);
    }

    // 3. POST with request body (JSON)
    @PostMapping("/message")
    public ResponseEntity<String> postMessage(@RequestBody MessagePayloadModel payload) {
        return ResponseEntity.ok("Received: " + payload);
    }

    // 4. POST with form data (x-www-form-urlencoded)
    @PostMapping("/form")
    public ResponseEntity<String> handleForm(@RequestParam String name, @RequestParam int age) {
        return ResponseEntity.ok("Form received: " + name + ", " + age);
    }

    // 5. PUT with header value
    @PutMapping("/header")
    public ResponseEntity<String> headerExample(@RequestHeader("X-Client-Id") String clientId) {
        return ResponseEntity.ok("Header received: " + clientId);
    }

    // 6. GET with matrix variables (must enable in config)
    @GetMapping("/matrix/{info}")
    public ResponseEntity<String> matrixVariables(@PathVariable String info,
                                                  @MatrixVariable(pathVar = "info") Map<String, String> matrixVars) {
        return ResponseEntity.ok("Path: " + info + ", Matrix Vars: " + matrixVars);
    }

    // 7. POST with multipart file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok("Uploaded file: " + file.getOriginalFilename());
    }
}
