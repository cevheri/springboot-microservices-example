package com.cevher.ms.salary.service.kafka.consumer;

import com.cevher.ms.SalaryMessage;
import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.service.SalaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryMessageConsumer {

    private final SalaryService salaryService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String KAFKA_TOPIC = "person-to-salary-2";

    /**
     * IMPORTANT!!! name is nat change
     * Kafka Consumer group : https://docs.confluent.io/platform/current/clients/consumer.html
     */
    private static final String KAFKA_CONSUMER_GROUP = "person-to-salary-group-2";

    @KafkaListener(topics = KAFKA_TOPIC
            , groupId = KAFKA_CONSUMER_GROUP
            //, containerFactory = "salaryKafkaListenerContainerFactory"
    )
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


        if (salaryMessage.getFromOperation().equals("sendFirstSalary")) {
            try {
                salaryService.createPersonSalarySpec(
                        salaryMessage.getPersonId(),
                        salaryMessage.getAmount()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            salaryMessage.setFromOperation("createPersonSalarySpec");
        } else if (salaryMessage.getFromOperation().equals("computeSalary")) {
            SalaryDto dto = null;
            try {
                dto = salaryService.computeSalary(
                        salaryMessage.getPersonId(),
                        salaryMessage.getSalaryDate()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            salaryMessage.setFromOperation("computeSalary");
            assert dto != null;
            salaryMessage.setAmount(dto.getAmount());
        }
        salaryMessage.setToOperation(toOperation);
        salaryMessage.setCode("0000");
        salaryMessage.setMessage("SUCCESS");
        salaryService.sendMessageToPerson(salaryMessage);
    }
}
//region
//    @KafkaListener(topics = KAFKA_TOPIC
//            //, groupId = KAFKA_CONSUMER_GROUP
//            , containerFactory = "salaryKafkaListenerContainerFactory"
//    )
//    public void consume(SalaryMessage message) {
//    }
//endregion