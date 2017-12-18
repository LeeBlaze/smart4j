package org.smart4j.bean;

import java.io.InputStream;

/**
 * Create by Lee on 2017/12/18
 */
public class FileParam {

    //文件表单的字段名
    private String fieldName;

    private String fileName;

    private long fileSize;

    private String contentType;

    private InputStream inputStream;

    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
