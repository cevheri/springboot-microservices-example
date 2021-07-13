package com.cevher.ms.salary.config.kafka;

import com.cevher.ms.SalaryMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

//@EnableKafka
//@Configuration
@Deprecated
public class KafkaConsumerConfiguration {


    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

//    public ConsumerFactory<String, String> consumerFactory(String groupId) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
//        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
//        return new DefaultKafkaConsumerFactory<>(props);
//    }

//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(groupId));
//        return factory;
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("foo");
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> barKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("bar");
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> headersKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("headers");
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> partitionsKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("partitions");
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> longMessageKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("longMessage");
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory("filter");
//        factory.setRecordFilterStrategy(record -> record.value()
//                .contains("World"));
//        return factory;
//    }

//    public ConsumerFactory<String, SalaryMessage> salaryConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "person-to-salary-groupx");
//        props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
//        props.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS,"false");
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SalaryMessage.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, SalaryMessage> salaryKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, SalaryMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(salaryConsumerFactory());
//        return factory;
//    }

}
