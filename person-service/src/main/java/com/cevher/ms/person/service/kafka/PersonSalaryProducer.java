package com.cevher.ms.person.service.kafka;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryProducer {
    private static final String KAFKA_TOPIC = "person-to-salary-1";

    //private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, com.cevher.ms.SalaryMessage> kafkaTemplateSalary;

//    public void produce(String message) {
//        kafkaTemplate.send(KAFKA_TOPIC, message);
//    }

    public void produce(com.cevher.ms.SalaryMessage message) {
        kafkaTemplateSalary.send(KAFKA_TOPIC, message);
    }
}
