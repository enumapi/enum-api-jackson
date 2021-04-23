package org.enumapi.app.service;

import org.enumapi.app.dto.SimpleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AppStubService implements AppService {

    private final ObjectMapper objectMapper;

    public AppStubService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public SimpleResponse askSomeMicroService(String type) {
        try {
            return objectMapper.readValue(
                    "{\n" +
                            "  \"occupation\": \"" + type + "\"\n" +
                            "}"
                    , SimpleResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
