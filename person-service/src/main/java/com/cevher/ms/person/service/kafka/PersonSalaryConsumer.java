package com.cevher.ms.person.service.kafka;

import com.cevher.ms.SalaryMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String KAFKA_TOPIC = "salary-to-person-2";

    /**
     * IMPORTANT!!!
     * Kafka Consumer group : https://docs.confluent.io/platform/current/clients/consumer.html
     */
    private static final String KAFKA_CONSUMER_GROUP = "salary-to-person-group-2";

    @KafkaListener(topics = KAFKA_TOPIC,
            groupId = KAFKA_CONSUMER_GROUP)
    public void consume(String message) {
        log.info("Consumer Group : " + message.toString());

        //TODO we should use json checker
        SalaryMessage salaryMessage = null;
        try {
            salaryMessage = objectMapper.readValue(message, SalaryMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert salaryMessage != null;
        String toOperation = salaryMessage.getFromOperation();



        log.info("Consumer Group (" + KAFKA_CONSUMER_GROUP + ") : " + message.toString());
        salaryMessage.setCode("0001");
        salaryMessage.setMessage("UNDEFINED");


        // doSomeThing()
        // if return to salary-service
        salaryMessage.setToOperation(toOperation);
        //personService.sendMessageToSalary(salaryMessage);
    }
}

