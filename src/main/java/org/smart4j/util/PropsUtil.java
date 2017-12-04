package org.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Create by Lee on 2017/12/4
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    private static Properties properties = new Properties();

    public static void loadProperties(String filename){

        InputStream inputStream;

        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load properties failed",e);
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String get(final String key){
        return get(key, "");
    }

    public static String get(final String key, String defaultValue){

        String value = defaultValue;
        if (properties.containsKey(key)){
            value = (String) properties.get(key);
        }
        return value;
    }
}
