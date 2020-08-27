package com.zz.jpatemplate.service;

import com.zz.jpatemplate.dao.BaseDao;
import com.zz.jpatemplate.domain.BaseDoMain;
import com.zz.region.domain.PageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 基础查询模块
 * {@link BaseDao} 为扩展查询，继承自Jpa的 JpaRepositoryImplementation
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 16:04
 */
public abstract class BaseService<T extends BaseDoMain,K extends BaseDao>{


    /**
     * 仓库位置
     */
    private K k;

    /**
     * 注入仓库位置
     * {@link BaseDao}
     * @param k
     */
    public BaseService(K k){
        this.k = k;
    }

    /**
     * 获取jpa原生方法
     * @return
     */
    public K k(){return k;}
    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    public Page<T> findPagesN(Integer currentPage, Integer pagesize){

        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)
        Sort.Direction sort =  Sort.Direction.ASC;
        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((currentPage-1)>=0?(currentPage-1):0, pagesize, sort, "id");

        Page<T> page = k.findAll(pageable);

        return page;
    }

    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    @Deprecated
    public PageData<T> findPages(Integer currentPage, Integer pagesize){

        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)
        Sort.Direction sort =  Sort.Direction.ASC;
        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((currentPage-1)>=0?(currentPage-1):0, pagesize, sort, "id");

        Page<T> page = k.findAll(pageable);
        ///获取该分页的列表
        List<T> list = page.getContent();

        PageData<T> pageData = new PageData<>();
        //获取总页数
        pageData.setPagesize(page.getTotalPages());
        //获取总元素个数
        pageData.setTotal(page.getTotalElements());
        pageData.setListData(list);


        return pageData;
    }

    /**
     * 条件分页查询
     * @param currentPage
     * @param pagesize
     * @param specification
     * @return
     */
    public Page<T> findPages(Integer currentPage, Integer pagesize, Specification<T> specification){

        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)
        Sort.Direction sort =  Sort.Direction.ASC;
        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((currentPage-1)>=0?(currentPage-1):0, pagesize, sort, "id");

        Page<T> page = k.findAll(specification,pageable);

        return page;
    }

    /**
     * 获取Specification条件
     * maps为参数集，key为字段，value为值集
     * maps的value是list，list第一个为符号位，所以查询的时候会忽略第一个未位
     * eq:全等
     * nq:不等
     * like:模糊查询
     * notlike:非模糊查询
     * @param maps
     * @return
     */
    public Specification<T> createSpecification(HashMap<String, List<Object>> maps,String symbol) {

        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //循环map记性验证
                for (String key:maps.keySet()){
                    if(null!=maps.get(key)){
                        List<Object> objects = maps.get(key);
                        //符号
                        //String symbol  = (String) objects.get(0);
                        //取出后移除第一个
                        //objects.remove(0);
                        switch (symbol){
                            case "eq":
                                //等于
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.equal(root.get(key), stro));
                                    }
                                });
                                break;
                            case "nq":
                                //不等于
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.notEqual(root.get(key), stro));
                                    }
                                });
                                break;
                            case "like":
                                //like
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.like(root.get(key), "%"+stro+"%"));
                                    }
                                });
                                break;
                            case "notlike":
                                //nolike
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.notLike(root.get(key), "%"+stro+"%"));
                                    }
                                });
                                break;
                            default:
                                new RuntimeException("没有对比到符号位，请使用别的方法");
                        }
                    }
                }
                System.out.println("predicates:"+predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return specification;
    }


}
