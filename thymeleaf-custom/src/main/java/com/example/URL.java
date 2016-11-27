package com.example;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Webアプリの全URLを定義するクラス<br>
 * リフレクションを使ってURLを集めているので、URL_MAP以外の定数は定義しないこと
 *
 * @author keke
 *
 */
public class URL {

    public static final String CSS = "/css";

    public static final String DEMO1 = "/demo1";

    public static final String DEMO2 = "/demo2";

    private static final Map<String, String> URL_MAP;

    static {
        Map<String, String> urlMap = new HashMap<>();
        Field[] fields = URL.class.getFields();

        for (Field field : fields) {
            if ("URL_MAP".equals(field.getName())) {
                continue;
            }

            try {
                String url =((String) field.get(null));
                urlMap.put(field.getName(), url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        URL_MAP = Collections.unmodifiableMap(urlMap);
    }

    public String get(String key) {
        return URL_MAP.getOrDefault(key, "");
    }

    public String get(String key, String filename) {
        return get(key) + "/" + filename;
    }

}
