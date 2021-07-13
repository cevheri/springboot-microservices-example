package com.cevher.ms.person.service.kafka;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryProducer {
    private static final String KAFKA_TOPIC = "person-to-salary-2";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        log.info("Person Kafka Produce: "+message);
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }


}
