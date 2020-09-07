package com.zz.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zz.entity.TestData;
import com.zz.listener.BaseListener;
import com.zz.listener.TestListener;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 17:44
 */
public class ExcelRead {

    /**
     * 读多个或者全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link TestData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link TestListener}
     * <p>
     * 3. 直接读即可
     * @param classes 类信息（对应的excel类）
     * @param listeners listener集合与classes一一对用
     * @param fileUrl 文件路径
     */
    public void repeatedRead(List<Class> classes, List<BaseListener> listeners, String fileUrl) {
        // 读取部分sheet
        String fileName = fileUrl;
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        read(excelReader,classes,listeners);
    }


    /**
     * 单sheet读取
     * <p>1. 创建excel对应的实体对象 参照{@link TestData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link TestListener}
     * <p>3. 直接读即可
     * @param c 类信息
     * @param b listener listener信息参照TestListener;
     * @param fileUrl 文件路径
     */
    public void simpleRead(Class c,BaseListener b,String fileUrl) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = fileUrl;
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        read(excelReader,c,b);
    }


    /**
     * web上传读取，单sheet
     * @param file 文件
     * @param c 类信息
     * @param b listener
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile file, Class c, BaseListener b) throws IOException {
        ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
        read(excelReader,c,b);
        return "success";
    }

    /**
     * web上传读取，多sheet
     * @param file 文件
     * @param classes 类信息
     * @param listeners listeners
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile file,List<Class> classes,List<BaseListener> listeners) throws IOException {

        ExcelReader excelReader = null;
        excelReader = getReader(file.getInputStream());
        read(excelReader,classes,listeners);
        return "success";
    }

    /**
     * 获取reader(String)
     * @param fileUrl 文件路径
     * @return
     */
    private ExcelReader getReader(String fileUrl){
        return EasyExcel.read(fileUrl).build();
    }
    /**
     * 获取reader(String)
     * @param in 输入流
     * @return
     */
    private ExcelReader getReader(InputStream in){
        return EasyExcel.read(in).build();
    }
    /**
     * 获取reader(String)
     * @param file 文件
     * @return
     */
    private ExcelReader getReader(File file){
        return EasyExcel.read(file).build();
    }

    /**
     * 多sheet读取
     * @param excelReader
     * @param classes
     * @param listeners
     */
    private void read(ExcelReader excelReader,List<Class> classes,List<BaseListener> listeners){

        //验证classes是否等于listeners
        if(listeners.size()!=listeners.size()){
            throw new RuntimeException("传入的classes与listeners长度不等，需传入数量相等");
        }

        try {
            //sheet引用
            ReadSheet readSheet = null;
            //sheets集合，存放sheet引用，便于下面统一读取
            List<ReadSheet> readSheets = new ArrayList<>();
            for (int i = 0,num = classes.size();i<num;i++){
                // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
                //sheetNo为readSheets.size()，也就是从0开始
                readSheet = EasyExcel.readSheet(i).head(classes.get(i)).registerReadListener(listeners.get(i)).build();
                readSheets.add(readSheet);
            }
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheets);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 单sheet读取
     * @param excelReader
     * @param c
     * @param b
     */
    private void read(ExcelReader excelReader, Class c, BaseListener b){
        try {
            ReadSheet readSheet = EasyExcel.readSheet(0).head(c).registerReadListener(b).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }


}
