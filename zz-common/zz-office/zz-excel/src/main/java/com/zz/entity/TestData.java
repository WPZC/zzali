package com.zz.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 16:19
 */
@Data
public class TestData extends BaseData{

    /**
     * 序号
     */
    @ExcelProperty("序号")
    private Integer sn;
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;
    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private Integer age;
    /**
     * 学校
     */
    @ExcelProperty("学校")
    private String school;
    /**
     * 班级编号
     */
    @ExcelProperty("班级")
    private Integer classSn;

}
