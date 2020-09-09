package com.zz.jpatemplate.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 15:05
 */
public interface BaseDao<T,ID> extends JpaRepositoryImplementation<T, ID> {

}
