package com.feiniaojin.ddd.eventsourcing.infrastructure.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化异常");
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON反序列化异常");
        }
    }
}
