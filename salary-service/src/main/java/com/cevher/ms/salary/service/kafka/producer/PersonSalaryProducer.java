package com.cevher.ms.salary.service.kafka.producer;

import com.cevher.ms.SalaryMessage;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryProducer {

    private static final String KAFKA_TOPIC = "salary-to-person-2";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }
}
