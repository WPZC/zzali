package com.zz.region.methods.utils;

import java.lang.reflect.Method;

/**
 * 反射方法区
 * @author wqy
 * @version 1.0
 * @date 2020/6/17 16:02
 */
public class InvokeUtils {

    /**
     * 通过反射获取值（方法）
     * @param <T> 传入泛型
     * @param <K> 返回泛型
     * @param t obj对象
     * @param methodPrefix 方法前缀
     * @param methodSuffix 方法后缀
     * @param retureType 返回值类型
     * @return
     * @throws Exception
     */
    public static  <T,K> K methodInvokeData(T t, String methodPrefix, int methodSuffix, K retureType) throws Exception {

        Method method = t.getClass().getMethod(methodPrefix+methodSuffix);
        //通过反射获取值
        K value = (K) method.invoke(t);

        return value;

    }

}
