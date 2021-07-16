package com.cevher.ms.salary.service.kafka.avro;
import com.cevher.ms.salary.avro.model.AvroHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@Slf4j
public class KafkaAvroResource {

    @Autowired
    KafkaAvroProducerService producerService;

    @PostMapping(value = "/send/avro/salary")
    public String kafkaMessage(@RequestBody AvroHttpRequest message) {
        producerService.sendMessage(message);
        return "Success";
    }
}
