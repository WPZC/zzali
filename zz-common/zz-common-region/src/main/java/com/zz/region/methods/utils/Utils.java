package com.zz.region.methods.utils;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/10 15:24
 */
public class Utils {

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isNull(Object object){

        if(null==object||"".equals(object)){
            return true;
        }

        return false;

    }

}
