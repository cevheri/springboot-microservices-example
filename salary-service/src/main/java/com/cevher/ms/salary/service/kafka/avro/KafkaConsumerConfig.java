package com.cevher.ms.salary.service.kafka.avro;

import com.cevher.ms.salary.avro.model.AvroHttpRequest;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrap.address:localhost:9092}")
    private String bootstrapAddress;

    @Value(value = "${kafka.consumer:salary-to-person-avro-group}")
    private String groupId;


    @Bean
    public ConsumerFactory<String, AvroHttpRequest> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new AvroDeserializer<>(AvroHttpRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AvroHttpRequest> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, AvroHttpRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
