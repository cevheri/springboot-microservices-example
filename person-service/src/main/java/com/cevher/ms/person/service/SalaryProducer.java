package com.cevher.ms.person.service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryProducer {
    private static final String KAFKA_TOPIC = "salary";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
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
