package org.smart4j.bean;

import java.util.Map;

/**
 * Create by Lee on 2017/12/12
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
