package com.cevher.ms.salary.service.kafka.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;


public class SalaryMessageSerde<SalaryMessage>
        implements Serde<SalaryMessage> {

    private final Serializer serializer = new SalaryMessageSerializer();
    private final Deserializer deserializer = new SalaryMessageDeserializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.serializer.configure(configs, isKey);
        this.deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        this.serializer.close();
        this.deserializer.close();
    }

    @Override
    public Serializer<SalaryMessage> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<SalaryMessage> deserializer() {
        return deserializer;
    }
}
