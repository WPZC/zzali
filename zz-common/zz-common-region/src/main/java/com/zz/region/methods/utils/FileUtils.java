package com.zz.region.methods.utils;

import lombok.Data;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/20 13:58
 */
public class FileUtils {

    /**
     * 查询该文件下的所有文件
     * @param file 文件路径
     * @param map   返回结果
     * @param partition 拆分标识
     */
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

    public static void main(String[] args) {

        File file = new File("F:\\桌面\\by\\智慧工地\\照片\\餐饮");

        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();

        fileList(file,map,1,"F:\\桌面\\by\\智慧工地\\照片\\餐饮","",false);

        System.out.println();

    }

    /**
     * 文件列表
     * @param file      初始文件路径
     * @param map       返回的map结果对象
     * @param count     需要的key在第几级文件夹
     * @param inputPath 初始对比路径，count为该路径的偏移量
     * @param name      传空字符串即可
     * @param isKey     是否获取了key，默认为false
     */
    public static void fileList(File file, LinkedHashMap<String, List<String>> map, Integer count, String inputPath, String name, boolean isKey){

        Assert.notNull(file,"文件不能为空！！！");
        //文件列表
        File[] files = file.listFiles();
        //判断是否是最底层文件
        if(ArrayUtils.isEmpty(files)){
            //进入到这步的时候说明已经到底了，最后一层必然是图片或者文件
            map.get(name).add(file.getPath());
            System.out.println("到底了，没文件");
            return;
        }
        for (File f:files){
            //计数，保留为当前递归层对象
            Integer c = count;
            //boolean iKey = false;
            //当inputPath与文件路径相同时，则子路径的文件名就是map的key
            if(file.getPath().equals(inputPath)&&c<=0){
                //获取当前文件名，当作key
                name = f.getName();
                map.put(name,new ArrayList<>());
                //修改isKey为true，说明已经获取到key，往后直接遍历到最后一层的图片，禁止修改inputPath
                isKey = true;
            }
            //只有没有找到key的时候此处才让修改，找到key以后走完当前层的递归
            if(!isKey){
                inputPath = f.getPath();
            }
            //递归，count-1
            fileList(f,map,--c,inputPath,name,isKey);
        }
    }

    /**
     * 处理web上传图片信息
     * @param stringMap 参数集
     * @param fileMap   文件集
     * @param t         赋值对象
     * @param fileUrlPrefix 文件前缀，例：http://****
     * @param saveUrl   文件保存路径，对应本地路径
     * @param <T>       参数类型
     * @throws NoSuchFieldException
     * @throws IOException
     */
    public static <T> void fileWeb(Map<String, String[]> stringMap,MultiValueMap<String, MultipartFile> fileMap,T t,String fileUrlPrefix,String saveUrl) throws NoSuchFieldException, IOException {

        /**
         * 通过反射设置属性值
         */
        for (String key:stringMap.keySet()){
            InvokeUtils.setValue(t,t.getClass(),key,t.getClass().getDeclaredField(key).getType(),stringMap.get(key)[0]);
        }

        /**
         * 设置文件
         */
        // 循环遍历，取出单个文件
        for (String key : fileMap.keySet()) {
            //存放文件，用于存库
            String files = "";
            // 获取文件列表
            List<MultipartFile> muls = fileMap.get(key);
            //遍历列表
            for (int i = 0,num = muls.size();i<num;i++){

                //取出单个文件
                MultipartFile mf = muls.get(i);

                // 文件名
                String fileName = mf.getOriginalFilename();
                String picName = fileName;
                // 后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //根据类型选择路径
                // 上传后的路径
                String filePath = saveUrl;
                // 新文件名
                fileName = UUID.randomUUID() + suffixName;
                String fileUrl = filePath+fileName;
                File dest = new File(fileUrl);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                mf.transferTo(dest);
                files = fileUrlPrefix + fileName + "," + files ;
            }
            //完成以后讲路径存入t
            InvokeUtils.setValue(t,t.getClass(),key,t.getClass().getDeclaredField(key).getType(),files);
        }
    }



}