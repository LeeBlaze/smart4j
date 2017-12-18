package org.smart4j.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Lee on 2017/12/12
 */
public class Param {

    private Map<String, Object> paramMap;

    private List<FormParam> formParamList;

    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 获取请求参数映射
     * @return
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<>();
        if (!fieldMap.isEmpty()){
            for (FormParam param:formParamList){
                String fieldName = param.getFieldName();
                Object fieldValue = param.getFieldValue();
                if (fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName)+ File.separator+fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取上传文件映射
     * @return
     */
    public Map<String, List<FileParam>> getFileMap(){
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (!fileParamList.isEmpty()){
            for (FileParam fileParam:fileParamList){
                String fieldName = fileParam.getFieldName();
                List<FileParam> fileParams = null;
                if (fileMap.containsKey(fieldName)){
                    fileMap.get(fieldName).add(fileParam);
                }else {
                    fileParams = new ArrayList<>();
                    fileParams.add(fileParam);
                }
                fileMap.put(fieldName, fileParams);
            }
        }

        return fileMap;
    }

    /**
     * 获取所有上传文件
     * @param fieldName
     * @return
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    public FileParam getFile(String fieldName){
        List<FileParam> fileParams = getFileList(fieldName);
        if (!fileParams.isEmpty()){
            return fileParams.get(0);
        }
        return null;
    }

}
