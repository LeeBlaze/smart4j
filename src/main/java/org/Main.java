package org;

import org.smart4j.util.AopHelper;
import org.smart4j.util.ClassUtil;
import org.smart4j.util.HelperLoader;

/**
 * Create by Lee on 2017/12/4
 */
public class Main {

    public static void main(String[] args) {

        HelperLoader.init();
        try {
            AopHelper.createProxyMap();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
