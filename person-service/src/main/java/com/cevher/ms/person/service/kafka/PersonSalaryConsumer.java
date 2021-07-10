package com.cevher.ms.person.service.kafka;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryConsumer {
    private static final String KAFKA_TOPIC = "salary-to-person-1";
    private static final String KAFKA_CONSUMER_GROUP = "salary-to-person-group-1";

    //    @KafkaListener(topics = KAFKA_TOPIC,
    //                  groupId = KAFKA_CONSUMER_GROUP)
    //    public void consume(String message) {
    //        log.info("Consumer Group : " + message.toString());
    //    }

    @KafkaListener(topics = KAFKA_TOPIC,
            groupId = KAFKA_CONSUMER_GROUP)
    public void consume(com.cevher.ms.SalaryMessage message) throws Exception {
        log.info("Person Service Consumer Group : " + message.toString());
    }
}

