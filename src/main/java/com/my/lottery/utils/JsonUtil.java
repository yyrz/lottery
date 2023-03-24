package com.my.lottery.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 抽象json转换工具类 使用ObjectMapper
 *
 * @author RenYu
 * @time 2021/8/26 11:25
 */
@Slf4j
@Component
public class JsonUtil {
    private static ObjectMapper objectMapper;

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return objectMapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(" toJsonString [{}] ,", object, e);
        }
        return null;
    }

    /**
     * json转对象
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> classOfT) {
        if(json==null){
            return null;
        }
        try {
            return objectMapper.reader().readValue(json, classOfT);
        } catch (IOException e) {
            String className = classOfT.getSimpleName();
            log.error(" parse json [{}] to class [{}] error：{}", json, className, e);
        }
        return null;
    }

    /**
     * json字符串转list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, getCollectionType(List.class, cls));
        } catch (JsonProcessingException e) {
            String className = cls.getSimpleName();
            log.error(" parse json [{}] to class [{}] error：{}", json, className, e);
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json字符串转成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        return jsonToObject(json, Map.class);
    }

    /**
     * json字符串转成ObjectNode
     *
     * @param json
     * @return
     */
    public static ObjectNode jsonToObjectNode(String json) {
        return jsonToObject(json, ObjectNode.class);
    }

    /**
     * json字符串转成ArrayNode
     *
     * @param json
     * @return
     */
    public static ArrayNode jsonToArrayNode(String json) {
        return jsonToObject(json, ArrayNode.class);
    }

    /**
     * 创建ObjectNode
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    /**
     * 创建ArrayNode
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    /**
     * 获取ObjectMapper
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }
}
