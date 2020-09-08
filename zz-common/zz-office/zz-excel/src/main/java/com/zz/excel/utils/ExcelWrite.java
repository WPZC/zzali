package com.zz.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.zz.entity.BaseData;
import com.zz.entity.TestData;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel写入工具类
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 17:46
 */
public class ExcelWrite {

    /**
     * 单sheet写
     * <p>1. 创建excel对应的实体对象 参照{@link com.zz.entity.TestData}
     * <p>2. 直接写即可
     */
    public void write(String fileUrlAndName, Class c, String sheetName, List<BaseData> datas) {

        String fileName = fileUrlAndName;
        // 写法2
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();;
        try {
            writeSheet(excelWriter,0,sheetName,c,datas);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 单sheet写
     * <p>1. 创建excel对应的实体对象 参照{@link com.zz.entity.TestData}
     * <p>2. 直接写即可
     */
    public void write(String fileUrlAndName, List<Class> classes, List<String> sheetNames, List<List<BaseData>> datas) {

        //验证classes是否等于listeners
        if(classes.size()!=sheetNames.size()){
            throw new RuntimeException("传入的classes与sheetNames长度不等，需传入数量相等");
        }

        String fileName = fileUrlAndName;
        // 写法2
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        excelWriter = EasyExcel.write(fileName).build();
        try {
            for (int i = 0,num = classes.size();i<num;i++){
                writeSheet(excelWriter,i,sheetNames.get(i),classes.get(i),datas.get(i));
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 单sheet下载
     *
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    public void webWrite(HttpServletResponse response,String fileName,Class c,String sheetName,List<BaseData> data) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), c).autoCloseStream(Boolean.FALSE).sheet(sheetName)
                    .doWrite(data);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", "11");
            map.put("msg", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }


    /**
     * 多sheet下载
     *
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    public void webWrite(HttpServletResponse response,String fileName,List<Class> classes,List<String> sheetNames,List<List<BaseData>> datas) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            //核心就是构建一个带OutputStream的ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).autoCloseStream(Boolean.FALSE).build();
            for (int i = 0,num = classes.size();i<num;i++){
                writeSheet(excelWriter,i,sheetNames.get(i),classes.get(i),datas.get(i));
            }
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", "11");
            map.put("msg", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    private void writeSheet(ExcelWriter excelWriter,int i,String sheetName,Class c,List<BaseData> datas){
        // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
        WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).head(c).build();
        excelWriter.write(datas, writeSheet);
    }

}
