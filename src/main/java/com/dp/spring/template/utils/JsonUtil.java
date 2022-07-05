package com.dp.spring.template.utils;

import com.dp.spring.template.utils.json.JsonFilterAnnotationIntrospecter;
import com.dp.spring.template.utils.json.JsonPropertyFilter;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = null;

    public JsonUtil() {
    }

    public static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        return mapper;
    }

    public static Map<String, Object> parseJsonObject(String json) {
        try {
            return (Map)getObjectMapper().readValue(json, HashMap.class);
        } catch (Exception var2) {
            logger.warn(var2.getMessage());
            return null;
        }
    }

    public static <T> T parseJsonObject(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception var3) {
            logger.warn(var3.getMessage());
            return null;
        }
    }

    public static List<Object> parseJsonArray(String json) {
        try {
            return (List)getObjectMapper().readValue(json, ArrayList.class);
        } catch (Exception var2) {
            logger.warn(var2.getMessage());
            return null;
        }
    }

    public static <T> List<T> parseJsonArray(String json, Class<T> clazz) {
        try {
            return (List)getObjectMapper().readValue(json, TypeFactory.defaultInstance().constructParametrizedType(List.class, List.class, new Class[]{clazz}));
        } catch (Exception var3) {
            logger.warn(var3.getMessage());
            return null;
        }
    }

    public static String toJson(Object o) {
        try {
            return getObjectMapper().writeValueAsString(o);
        } catch (Exception var2) {
            logger.warn(var2.getMessage());
            return null;
        }
    }

    public static String toJson(Object o, Module module) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(module);
            return objectMapper.writeValueAsString(o);
        } catch (Exception var3) {
            logger.warn(var3.getMessage());
            return null;
        }
    }

    public static String toJson(Object o, Class mixinClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.addMixIn(o.getClass(), mixinClass);
            return objectMapper.writeValueAsString(o);
        } catch (Exception var3) {
            logger.warn(var3.getMessage());
            return null;
        }
    }

    public static String toJson(Object o, Map<Class<?>, Class<?>> mixins) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setMixIns(mixins);
            return objectMapper.writeValueAsString(o);
        } catch (Exception var3) {
            logger.warn(var3.getMessage());
            return null;
        }
    }

    public static String toJson(Object o, Map<Class<?>, Class<?>> mixins, boolean enablePropertyFilter) {
        return enablePropertyFilter ? toJson(o, mixins, new JsonPropertyFilter()) : toJson(o, mixins);
    }

    public static String toJson(Object o, Map<Class<?>, Class<?>> mixins, PropertyFilter filter) {
        try {
            AnnotationIntrospector introspector = new JsonFilterAnnotationIntrospecter();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setMixIns(mixins);
            objectMapper.setAnnotationIntrospector(introspector);
            FilterProvider filters = (new SimpleFilterProvider()).addFilter(JsonFilterAnnotationIntrospecter.DEFAULT_FILTER_ID, filter);
            return objectMapper.writer(filters).writeValueAsString(o);
        } catch (Exception var6) {
            logger.warn(var6.getMessage());
            return null;
        }
    }
}

