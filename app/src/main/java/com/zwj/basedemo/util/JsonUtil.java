package com.zwj.basedemo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static <T> T getObject(String jsonString, Class<T> cls) {
		return JSON.parseObject(jsonString, cls);
	}

	public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
		return JSON.parseArray(jsonString, cls);
	}

	public static List<Map<String, String>> getKeyMapsList(String jsonString) {
		List<Map<String, String>> list;
		list = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String, String>>>() {
				});
		return list;
	}
}
