package com.zz.jpatemplate.dao;

import com.zz.jpatemplate.domain.BaseDoMain;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 15:05
 */
public interface BaseDao<T extends BaseDoMain,ID> extends JpaRepositoryImplementation<T, ID> {

}
