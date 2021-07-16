package com.cevher.ms.salary.service.kafka.avro;

import com.cevher.ms.salary.avro.model.AvroHttpRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroMessageConsumer {

    @KafkaListener(topics = "salary-to-person-avro", groupId = "salary-to-person-avro-group")
    public void listen(AvroHttpRequest message) {
        log.info("Received Messasge in group : {}", message);
    }
}
