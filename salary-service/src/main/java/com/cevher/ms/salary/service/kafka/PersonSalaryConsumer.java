package com.cevher.ms.salary.service.kafka;

import com.cevher.ms.SalaryMessage;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSalaryConsumer {



    //private final SalaryService salaryService;
    private static final String KAFKA_TOPIC = "person-to-salary-1";
    private static final String KAFKA_CONSUMER_GROUP = "person-to-salary-group-1";

//        @KafkaListener(topics = KAFKA_TOPIC
//                //, groupId = KAFKA_CONSUMER_GROUP
//                , containerFactory = "salaryKafkaListenerContainerFactory"
//        )
        public void consume(String message) {
            log.info("Consumer Group : " + message.toString());
        }

    @KafkaListener(topics = KAFKA_TOPIC
            //, groupId = KAFKA_CONSUMER_GROUP
            , containerFactory = "salaryKafkaListenerContainerFactory"
    )
    public void consume(SalaryMessage message) {
        log.info("Consumer Group : " + message.toString());
//        SalaryMessage salaryMessage = SalaryMessage.builder()
//                .toOperation(message.getFromOperation())
//                .personId(message.getPersonId())
//                .amount(message.getAmount())
//                .salaryDate(message.getSalaryDate())
//                .code("0001")
//                .message("UNDEFINED")
//                .build();

//
//        if (message.getFromOperation().equals("sendFirstSalary")) {
//            salaryService.createPersonSalarySpec(
//                    message.getPersonId(),
//                    message.getAmount()
//            );
//            salaryMessage.setFromOperation("createPersonSalarySpec");
//        } else if (message.getFromOperation().equals("computeSalary")) {
//            var dto = salaryService.computeSalary(
//                    message.getPersonId(),
//                    message.getSalaryDate()
//            );
//            salaryMessage.setFromOperation("computeSalary");
//            salaryMessage.setAmount(dto.getAmount());
//        }
//        salaryMessage.setCode("0000");
//        salaryMessage.setMessage("SUCCESS");
//        salaryService.sendMessageToPerson(salaryMessage);


    }
}