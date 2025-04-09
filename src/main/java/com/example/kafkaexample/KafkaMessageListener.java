package com.example.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(ProducerExampleRequest message) {
        System.out.println("🔥 Received: " + message);
    }
}
