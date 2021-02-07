package io.trajano.reactivespring.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String parse(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to String");
        }
    }

}
