package com.jungle.service.application.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;

/**
 * @author 杨文军(132500)
 * @version Created on 2016/4/27.
 */
public class DefaultJsonMapper {
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, false);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
    }

    private DefaultJsonMapper() {}
    public static ObjectMapper getMapper() {
        return mapper;
    }
    public static void setMapper(ObjectMapper mapper) {
        mapper = mapper;
    }

    public static <T> T parse(String json, Class<T> objectType) throws IOException {
        if(json == null) {
            return null;
        } else {
            Assert.notNull(objectType, "objectType cannot be null.");
            return mapper.readValue(json, objectType);
        }
    }

    public static <T> T parse(InputStream stream, Class<T> objectType) throws IOException {
        Assert.notNull(stream, "stream cannot be null.");
        Assert.notNull(objectType, "objectType cannot be null.");
        return mapper.readValue(stream, objectType);
    }

    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
