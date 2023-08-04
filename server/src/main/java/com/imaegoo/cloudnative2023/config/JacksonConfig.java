package com.imaegoo.cloudnative2023.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.sql.Timestamp;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Timestamp.class, new DateDeserializers.TimestampDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
