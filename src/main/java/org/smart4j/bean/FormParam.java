package org.smart4j.bean;

/**
 * Create by Lee on 2017/12/18
 */
public class FormParam {

    private String fieldName;

    private String fieldValue;

    public FormParam(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
