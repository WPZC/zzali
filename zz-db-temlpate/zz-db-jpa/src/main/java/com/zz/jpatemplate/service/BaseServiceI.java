package com.zz.jpatemplate.service;

import com.zz.domain.PageData;
import com.zz.domain.PageInfo;
import com.zz.domain.ParamInfo;
import com.zz.jpatemplate.dao.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/21 15:04
 */
public interface BaseServiceI<T,K extends BaseDao> {

    /**
     * 获取仓库
     * @return
     */
    K getK();

    /**
     * 根据ID查询
     * @param Id
     * @return
     */
    T findById(Long Id);

    /**
     * 根据ID删除
     * @param Id
     */
    void deleteById(Long Id);

    /**
     * 修改
     * @param t
     */
    void updateData(T t);

    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    Page<T> findPagesN(Integer currentPage, Integer pagesize);

    /**
     * 分页查询(正序)
     * @param currentPage
     * @return
     */
    PageData<T> findPages(Integer currentPage, Integer pagesize);

    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    PageData<T> findPages(Integer currentPage, Integer pagesize, Sort.Direction sort);

    /**
     * 分页查询，条件分页查询
     * @param currentPage
     * @return
     */
    @Deprecated
    PageData<T> findPages(Integer currentPage, Integer pagesize, Sort.Direction sort, Specification<T> specification);

    /**
     * 条件分页查询
     * @param currentPage 页码
     * @param pagesize  一页显示多少条
     * @param sort 排序规则
     * @param specification 条件
     * @return
     */
    @Deprecated
    PageData<T> findByPages(Integer currentPage, Integer pagesize,Sort.Direction sort,Specification<T> specification);

    /**
     * 无条件分页
     * @param currentPage
     * @param pagesize
     * @param sort
     * @return
     */
    @Deprecated
    PageData<T> findByPages(Integer currentPage, Integer pagesize,Sort.Direction sort);

    /**
     * 根据pageInfo查询
     * @param pageInfo
     * @return
     */
    PageData<T> findByPages(PageInfo pageInfo) throws Exception;

    /**
     * 条件分页查询
     * @param currentPage
     * @param pagesize
     * @param specification
     * @return
     */
    @Deprecated
    Page<T> findPages(Integer currentPage, Integer pagesize, Specification<T> specification);

    /**
     * 不分页条件查询
     * @param specification 条件
     * @return
     */
    List<T> findNoPageList(Specification<T> specification);
}
