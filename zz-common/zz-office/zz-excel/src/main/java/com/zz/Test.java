package com.zz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zz.entity.BaseData;
import com.zz.entity.TestData;
import com.zz.excel.utils.ExcelUtils;
import com.zz.listener.BaseListener;
import com.zz.listener.TestListener;

import java.io.File;
import java.util.*;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 16:23
 */
public class Test {

    public static void main(String[] args) {
        repeatedRead();
    }

    /**
     * 读多个或者全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link TestData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link TestListener}
     * <p>
     * 3. 直接读即可
     */
    public static void repeatedRead() {

        // 读取部分sheet
        String fileName = "F:\\桌面\\test.xlsx";

        List<Class> classes = new ArrayList<>();
        List<BaseListener> listeners = new ArrayList<>();
        classes.add(TestData.class);
        classes.add(TestData.class);
        listeners.add(new TestListener());
        listeners.add(new TestListener());

        ExcelUtils excelUtils = new ExcelUtils();

        excelUtils.repeatedRead(classes,listeners,fileName);
    }


}
