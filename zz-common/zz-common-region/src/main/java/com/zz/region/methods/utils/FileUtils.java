package com.zz.region.methods.utils;

import java.io.File;
import java.util.HashMap;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/20 13:58
 */
public class FileUtils {

    public static void findall(File file, HashMap<String,String> map,String partition) {
        if(!file.exists()){
            System.out.print("文件不存在！");
        }
        if(file.isFile()) {

            String value = file.getPath().substring((file.getPath().indexOf(partition)));

            value = value.replaceAll("\\\\", "//");

            map.put(file.getName().replaceAll("[.][^.]+$", ""),value);
        }else {
            File[] files=file.listFiles();
            if(null==files||files.length==0) {
                System.out.println("no file");
            }else {
                for(File f:files) {
                    findall(f,map,partition);
                }
            }
        }
    }

}
