package com.cevher.ms.salary.service.kafka.consumer;

import com.cevher.ms.SalaryMessage;
import com.cevher.ms.salary.config.KafkaProperties;
import com.cevher.ms.salary.service.kafka.DeserializationError;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalaryMessageConsumer
        extends GenericConsumer<SalaryMessage> {

    //private final SalaryService salaryService;

    private static final String KAFKA_TOPIC = "person-to-salary-1";
    private static final String KAFKA_CONSUMER_GROUP = "person-to-salary-group-1";

    public SalaryMessageConsumer(final String topicName,
                                 final KafkaProperties kafkaProperties) {
        super(topicName,
                kafkaProperties.getConsumer().get(topicName),
                kafkaProperties.getPollingTimeout());
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String,
                Either<DeserializationError, SalaryMessage>> record) {

        // TODO /!\ Maybe you could delete the next log calls to avoid disclosing personal user information

        final Either<DeserializationError, SalaryMessage> value = record.value();

        if (value == null) {
            log.error("Null value in record {}", record);
            return;
        }

        if (value.isLeft()) {
            log.error("Deserialization record failure: {}", value.getLeft());
            return;
        }

        log.info("Handling record: {}", value.get());

        // TODO: Here is where you can handle your records
    }

    @Bean
    public void executeKafkaFooRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }




//        @KafkaListener(topics = KAFKA_TOPIC
//                //, groupId = KAFKA_CONSUMER_GROUP
//                , containerFactory = "salaryKafkaListenerContainerFactory"
//        )
//    public void consume(String message) {
//        log.info("Consumer Group : " + message.toString());
//    }
//
//    @KafkaListener(topics = KAFKA_TOPIC
//            //, groupId = KAFKA_CONSUMER_GROUP
//            , containerFactory = "salaryKafkaListenerContainerFactory"
//    )
//    public void consume(SalaryMessage message) {
//        log.info("Consumer Group : " + message.toString());
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
//        }


}