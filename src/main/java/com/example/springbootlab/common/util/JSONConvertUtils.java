package com.example.springbootlab.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;

// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.json
public class JSONConvertUtils {
    static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.registerModule(new JavaTimeModule());
    }
    public static String asString(Object object) {
        
        // 不使用时间差的方式  WRITE_DATE_KEYS_AS_TIMESTAMPS:将日期键作为时间戳写入 改为false
        // mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 指定日期格式
        // mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T asJSONObject(String jsonString, Class<T> clz) {
        try {
            return mapper.readValue(jsonString, clz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
