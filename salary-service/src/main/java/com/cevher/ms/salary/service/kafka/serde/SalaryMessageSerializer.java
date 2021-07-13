package com.cevher.ms.salary.service.kafka.serde;
import com.cevher.ms.SalaryMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class SalaryMessageSerializer
        implements Serializer<SalaryMessage> {

    private final ObjectMapper objectMapper;

    public SalaryMessageSerializer() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .setDateFormat(new StdDateFormat());
    }

    @Override
    public byte[] serialize(final String topicName,
                            final SalaryMessage data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (final JsonProcessingException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }
}
