package com.zz.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zz.entity.TestData;
import com.zz.excel.utils.ExcelRead;
import com.zz.excel.utils.ExcelWrite;
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
 * @date 2020/9/7 16:35
 */
public class ExcelUtils {

    /**
     * excel读
     */
    public final static ExcelRead EXCEL_READ;
    /**
     * excel写
     */
    public final static ExcelWrite EXCEL_WRITE;

    static {
        EXCEL_READ = new ExcelRead();
        EXCEL_WRITE = new ExcelWrite();
    }


}
