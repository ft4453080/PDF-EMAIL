package com.highrock.pdf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;


public class JSONHelper {
    public static Map<String, Object> convertJsonStrToMap(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>(){});
    }

    public static List<Map<String, Object>> convertJsonStrToList(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
    }

}
