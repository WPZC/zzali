package com.zz.region.methods.utils;

import java.util.LinkedHashMap;

/**
 * map工具
 * @author wqy
 * @version 1.0
 * @date 2020/7/16 10:09
 */
public class MapUtils {

    /**
     * 根据内层的key统计内外层所有value（Integer）数量
     * 例如外层为街道（11种，外层map），每种街道下有若然行业（内层map）
     * 然后根据内层的key（每个行业），分别从每个街道中抽取出对应的数量，得出返回值
     * @param maps
     * @return
     */
    public static LinkedHashMap<String,Integer> mapConversionNum(LinkedHashMap<String,LinkedHashMap<String,Integer>> maps){

        LinkedHashMap<String,Integer> rsMap = new LinkedHashMap<>();

        maps.forEach((key,map)->{
            //map为业态
            map.forEach((keyYt,value)->{
                //判断map中是否含有该key
                if(rsMap.containsKey(keyYt)){
                    Integer count = rsMap.get(keyYt);
                    count = count + value;
                    rsMap.put(keyYt,count);
                }else{
                    rsMap.put(keyYt,1);
                }
            });
        });

        return rsMap;

    }

}
