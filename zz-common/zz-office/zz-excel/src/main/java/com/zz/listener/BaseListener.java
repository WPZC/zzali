package com.zz.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.zz.entity.BaseData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 有个很重要的点 BaseListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去,
 * 如果使用excel导入，则需要实现该类，然后可以自定义规则，也可以调用super，
 * 但存库方法一定要自定义。
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 15:58
 */
@Slf4j
public abstract class BaseListener<T extends BaseData> extends AnalysisEventListener<T> {

    /**
     * 单次存库上限数
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 2;
    /**
     * 表格数据集合
     */
    List<T> listData = new ArrayList<T>();

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        listData.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (listData.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            listData.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("数据解析完成");

    }
    /**
     * 这里会一行行的返回头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }

    /**
     * 加上存储数据库，达到{BATCH_COUNT}后会触发这个方法
     */
    public abstract void saveData();
}
