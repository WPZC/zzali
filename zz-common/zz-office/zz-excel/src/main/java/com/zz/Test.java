package com.zz;

import com.zz.entity.BaseData;
import com.zz.entity.TestData;
import com.zz.excel.ExcelUtils;
import com.zz.listener.BaseListener;
import com.zz.listener.TestListener;

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

        ExcelUtils.EXCEL_READ.repeatedRead(classes,listeners,fileName);

        List<String> sheetNames = new ArrayList<>();
        sheetNames.add("测试1");
        sheetNames.add("测试2");
        TestData t = new TestData();
        t.setAge(11);
        t.setClassSn(1);
        t.setName("C");
        t.setSchool("dwa");
        t.setSn(1);
        List<BaseData> list = new ArrayList<>();
        list.add(t);
        TestData t1 = new TestData();
        t1.setAge(11);
        t1.setClassSn(1);
        t1.setName("C");
        t1.setSchool("dwa");
        t1.setSn(1);
        List<BaseData> list1= new ArrayList<>();
        list1.add(t1);

        List<List<BaseData>> lists= new ArrayList<>();
        lists.add(list);
        lists.add(list1);
        ExcelUtils.EXCEL_WRITE.write("F:\\桌面\\测试.xlsx",classes,sheetNames,lists);
    }


}
