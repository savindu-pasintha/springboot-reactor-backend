package com.example.springbootreactorbackend.event_driven.kafka_sample.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePayloadModel {
    private String id;
    private String content;
    @Override
    public String toString() {
        return "CustomMessagePayload[id=" + id + ", content=" + content + "]";
    }

//    public String getContent() {
//        return content;
//    }
//    public String getId(){
//        return id;
//    }
}
