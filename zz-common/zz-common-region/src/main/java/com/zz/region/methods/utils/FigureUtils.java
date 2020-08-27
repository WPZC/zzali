package com.zz.region.methods.utils;

import java.text.DecimalFormat;

/**
 * 数字工具
 * @author wqy
 * @version 1.0
 * @date 2020/6/23 14:18
 */
public class FigureUtils {

    /**
     * 获取两数之间的整单位数
     * @param param 参数
     * @param unit 单位（以什么为单位，例如10,100）
     * @return
     */
    public static Long getFigureBetween(Long param,Long unit){

        if(param<=unit){
            return unit;
        }else{
            //此处—+1是要向上取整
            return ((param/unit)+1)*unit;
        }

    }

    /**
     * doule转为两位小数
     * @param d
     * @return
     */
    public static Double getDoubleTwoDecimalNumber(double d){

        DecimalFormat df = new DecimalFormat("#.00");

        return Double.parseDouble(df.format(d));

    }

    /**
     * 将d转成double
     * @param d
     * @return
     */
    public static Double parseDouble(String d){

        if(null == d){
            return 0.0;
        }

        if("".equals(d)){
            return 0.0;
        }
        return Double.parseDouble(d);

    }
}
