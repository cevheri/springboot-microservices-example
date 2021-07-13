package com.cevher.ms.salary.service.kafka.serde;

import com.cevher.ms.SalaryMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class SalaryMessageDeserializer implements Deserializer<SalaryMessage> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SalaryMessage deserialize(String s, byte[] value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.readValue(new String(value, "UTF-8"), SalaryMessage.class);
        } catch (Exception e) {
            log.error("Unable to deserialize message {}", value, e);
            return null;
        }
    }
}
