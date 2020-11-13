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

    /**
     * 处理字符串  如：  abc_dex ---> abcDex
     * @param str
     * @return
     */
    public static  String removeLine(String str){
        if(null != str && str.contains("_")){
            int i = str.indexOf("_");
            char ch = str.charAt(i+1);
            char newCh = (ch+"").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i+1), newCh);
            String newStr2 = newStr.replace("_", "");
            return newStr2;
        }
        return str;
    }

}
