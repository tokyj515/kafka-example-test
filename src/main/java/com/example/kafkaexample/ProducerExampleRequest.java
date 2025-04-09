package com.example.kafkaexample;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducerExampleRequest {
    private String name;
    private int age;
}