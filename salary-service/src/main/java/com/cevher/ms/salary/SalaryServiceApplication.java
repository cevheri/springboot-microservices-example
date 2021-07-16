package com.cevher.ms.salary;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import java.util.Map;

@SpringBootApplication
public class SalaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryServiceApplication.class, args);
    }


    @Bean NewTopic newJsonSalaryTopic() { return new NewTopic("person-to-salary",1, (short) 1); }
    @Bean NewTopic newJsonPersonTopic() { return new NewTopic("salary-to-person",1, (short) 1); }
}
