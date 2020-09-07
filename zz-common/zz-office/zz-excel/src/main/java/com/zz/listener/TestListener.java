package com.zz.listener;

import com.zz.entity.TestData;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/7 16:19
 */
public class TestListener<TestData> extends BaseListener {

    @Override
    public void saveData() {
        System.out.println(super.listData);
    }
}
