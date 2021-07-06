package com.summit.util;

import org.springframework.context.ApplicationContext;

/**
 * @author: jeerper
 * @date: 2021-07-06 17:02:27
 */
public class ApplicationContextProvider {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 bean
     * @param name
     * @return
     */
    public static  Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过clazz获取 bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T>  T  getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     *通过clazz、name获取 bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T>  T  getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }


}
