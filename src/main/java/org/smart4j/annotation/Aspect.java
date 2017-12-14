package org.smart4j.annotation;

import java.lang.annotation.*;

/**
 * Create by Lee on 2017/12/13
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
