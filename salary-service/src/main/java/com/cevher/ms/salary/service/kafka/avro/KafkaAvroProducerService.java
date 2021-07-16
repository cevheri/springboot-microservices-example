package com.cevher.ms.salary.service.kafka.avro;

import com.cevher.ms.salary.avro.model.AvroHttpRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaAvroProducerService {

    @Value(value = "${kafka.topic.name:salary-to-person-avro}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, AvroHttpRequest> kafkaTemplate;

    public void sendMessage(AvroHttpRequest message) {
        ListenableFuture<SendResult<String, AvroHttpRequest>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(
                new ListenableFutureCallback<SendResult<String, AvroHttpRequest>>() {
                    @Override
                    public void onSuccess(SendResult<String, AvroHttpRequest> result) {
                        log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                    }
                });
    }
}

