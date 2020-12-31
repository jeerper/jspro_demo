package com.summit.annotation;

import java.lang.annotation.*;

/**
 * @author: jeerper
 * @since: 2020/12/23 17:01
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserOperate {
}
