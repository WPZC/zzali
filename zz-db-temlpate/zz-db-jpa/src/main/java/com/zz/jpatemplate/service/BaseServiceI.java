package com.zz.jpatemplate.service;

import com.zz.jpatemplate.dao.BaseDao;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/21 15:04
 */
public interface BaseServiceI<T,K extends BaseDao> {

    K getK();

}
