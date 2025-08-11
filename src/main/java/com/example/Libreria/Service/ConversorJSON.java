package com.example.Libreria.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorJSON {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
