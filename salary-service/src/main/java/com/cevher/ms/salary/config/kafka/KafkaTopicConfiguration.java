package com.cevher.ms.salary.config.kafka;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

//@Configuration
@Deprecated
public class KafkaTopicConfiguration {
    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

//    @Value(value = "${message.topic.name}")
//    private String topicName;
//
//    @Value(value = "${long.message.topic.name}")
//    private String longMsgTopicName;
//
//    @Value(value = "${partitioned.topic.name}")
//    private String partitionedTopicName;
//
//    @Value(value = "${filtered.topic.name}")
//    private String filteredTopicName;

    @Value(value = "${salary.topics.person-to-salary-topic-name}")
    private String personToSalaryTopicName;

    @Value(value = "${salary.topics.salary-to-person-topic-name}")
    private String salaryToPersonTopicName;

    //@Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

//    @Bean
//    public NewTopic topic1() {
//        return new NewTopic(topicName, 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic2() {
//        return new NewTopic(partitionedTopicName, 6, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic3() {
//        return new NewTopic(filteredTopicName, 1, (short) 1);
//    }

    //@Bean
    public NewTopic salaryToPersonTopicName() {
        return new NewTopic(salaryToPersonTopicName, 1, (short) 1);
    }

    //@Bean
    public NewTopic personToSalaryTopicName() {
        return new NewTopic(personToSalaryTopicName, 1, (short) 1);
    }

//    @Bean
//    public NewTopic topic5() {
//        NewTopic newTopic = new NewTopic(longMsgTopicName, 1, (short) 1);
//        Map<String, String> configs = new HashMap<>();
//        configs.put("max.message.bytes", "20971520");
//        newTopic.configs(configs);
//        return newTopic;
//    }
}
