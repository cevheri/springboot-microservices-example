package com.cevher.ms.salary.service.kafka.serde;

import com.cevher.ms.SalaryMessage;
import com.cevher.ms.salary.service.kafka.DeserializationError;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.vavr.control.Either;

import java.io.IOException;

@Slf4j
public class SalaryMessageDeserializer
        implements Deserializer<Either<DeserializationError, SalaryMessage>> {

    private final ObjectMapper objectMapper;

    public SalaryMessageDeserializer() {
        this.objectMapper =
                new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule())
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .setDateFormat(new StdDateFormat());
    }

    @Override
    public Either<DeserializationError, SalaryMessage> deserialize(final String topicName, final byte[] data) {
        try {
            final SalaryMessage value = objectMapper.readValue(data, SalaryMessage.class);
            return Either.right(value);
        } catch (final IOException e) {
            return Either.left(new DeserializationError(data, e));
        }
    }
}
