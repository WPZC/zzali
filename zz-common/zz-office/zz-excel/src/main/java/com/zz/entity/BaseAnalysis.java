package com.zz.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 动态保存解析
 * @author wqy
 * @version 1.0
 * @date 2020/6/17 8:58
 */
@Data
public class BaseAnalysis {

    /**
     * 数据集
     */
    private List<LinkedHashMap<String, List<String>>> maps;
    /**
     * 表名
     */
    private String tableName;

}