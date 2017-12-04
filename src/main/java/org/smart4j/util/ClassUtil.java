package org.smart4j.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by Lee on 2017/12/4
 */
public class ClassUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized){

        try {
            return Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("loadClass failed",e);
            e.printStackTrace();
        }

        return null;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){

                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)){
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet, packagePath, packageName);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return classSet;
    }

    public static void addClass(Set<Class<?>> classSet, String packagePath, String packageName){

        File[] files = new File(packagePath).listFiles((file)->file.isFile() && file.getName().endsWith(".class") ||file.isDirectory());

        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (!className.isEmpty()){
                    className = packageName + "." + className;
                    doAddClass(classSet,className);
                }
            }else {
                String subPackagePath = fileName;
                if (!subPackagePath.isEmpty()){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subpackageName = fileName;
                if (!subpackageName.isEmpty()){
                    subpackageName = packageName + "." + subpackageName;
                }
                addClass(classSet, subPackagePath, subpackageName);
            }
        }

    }

    public static void doAddClass(Set<Class<?>> classSet, String className){
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }



}
