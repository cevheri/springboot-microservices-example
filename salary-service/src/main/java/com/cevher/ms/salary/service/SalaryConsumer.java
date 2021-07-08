package com.cevher.ms.salary.service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryConsumer {

    private static final String KAFKA_TOPIC = "salary";
    private static final String KAFKA_CONSUMER_GROUP = "salary_group";

    @KafkaListener(topics = KAFKA_TOPIC,
            groupId = KAFKA_CONSUMER_GROUP)
    public void consume(String message) {
        log.info("Consumer Group : " + message.toString());
    }
}

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder()
class SalaryMessage implements Serializable {

    private String toService;
    private String fromService;
    private String uuid;

    @Getter
    @Setter
    private Long personId;

    @Getter
    @Setter
    private Double amount;

    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    public String getFromService() {
        return "person-service";
    }

    public String getToService() {
        return "salary-service";
    }
}