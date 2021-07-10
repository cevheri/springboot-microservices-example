package com.cevher.ms.salary.service.kafka;

import com.cevher.ms.SalaryMessage;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryProducer {
    private static final String KAFKA_TOPIC = "salary_to_person-1";

    //private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, SalaryMessage> kafkaTemplateSalary;

//    public void produce(String message) {
//        kafkaTemplate.send(KAFKA_TOPIC, message);
//    }

    public void produce(SalaryMessage message) {
        kafkaTemplateSalary.send(KAFKA_TOPIC, message);
    }
}
