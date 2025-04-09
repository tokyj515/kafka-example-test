package com.example.kafkaexample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ProducerExampleRequest request) {
        // type 헤더를 명시적으로 추가
        Message<ProducerExampleRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "test-topic")
                .setHeader("type", "test-message") // 👈 요게 핵심!
                .build();

        kafkaTemplate.send(message);
        return ResponseEntity.ok("✅ Sent!");
    }
}
