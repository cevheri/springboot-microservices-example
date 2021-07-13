package com.cevher.ms.salary.service.kafka.serde;

import com.cevher.ms.SalaryMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class SalaryMessageSerializer implements Serializer<SalaryMessage> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String arg0, SalaryMessage arg1) {
        byte[] retVal = null;
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            log.error("Unable to deserialize message {}", arg1, e);
        }
        return retVal;
    }
}
