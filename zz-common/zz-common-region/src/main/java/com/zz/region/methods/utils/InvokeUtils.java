package com.zz.region.methods.utils;

import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

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
    public static <T,K> K methodInvokeData(T t, String methodPrefix, int methodSuffix, K retureType) throws Exception {

        Method method = t.getClass().getMethod(methodPrefix+methodSuffix);
        //通过反射获取值
        K value = (K) method.invoke(t);

        return value;

    }

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
    public static <T,K> K methodInvokeData(T t, String methodPrefix, String methodSuffix, K retureType) throws Exception {

        Method method = t.getClass().getMethod(methodPrefix+methodSuffix);
        //通过反射获取值
        K value = (K) method.invoke(t);

        return value;

    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     * @param obj 对象
     * @param clazz 对象的class
     * @param filedName 字段名
     * @param typeClass 字段类型
     * @param value 值
     */
    public static void setValue(Object obj,Class<?> clazz,String filedName,Class<?> typeClass,Object value){
        filedName = Utils.removeLine(filedName);
        String methodName = "set" + filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        try{
            Method method =  clazz.getMethod(methodName, new Class[]{typeClass});
            method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 通过class类型获取获取对应类型的值
     * @param typeClass class类型
     * @param value 值
     * @return Object
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value){
        if(typeClass == int.class || value instanceof Integer || typeClass == Integer.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == short.class || value instanceof Short){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == byte.class || value instanceof Byte){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == double.class || value instanceof Double){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == long.class || value instanceof Long || typeClass == Long.class){
            if(null == value){
                return 0;
            }
            //判断是否是字符串
            if(value.getClass() == String.class){
                return Long.parseLong(String.valueOf(value));
            }
            Assert.hasText(value+"","没有找到参数类型，无法转换");
            return value;
        }else if(typeClass == String.class){
            if(null == value){
                return "";
            }
            return value;
        }else if(typeClass == boolean.class || value instanceof Boolean){
            if(null == value){
                return true;
            }
            return value;
        }else if(typeClass == BigDecimal.class){
            if(null == value){
                return new BigDecimal(0);
            }
            return new BigDecimal(value+"");
        }else if(typeClass == Date.class){
            if(null == value){
                return null;
            }
            return (Date)value;
        }else {
            return typeClass.cast(value);
        }
    }







}
