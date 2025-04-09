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
        System.out.println("📦 요청 수신됨: " + request);

        Message<ProducerExampleRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "test-topic")
                .setHeader("type", "test-message")
                .build();

        kafkaTemplate.send(message);

        System.out.println("📤 Kafka 메시지 전송 완료");

        return ResponseEntity.ok("✅ Sent!");
    }

}
