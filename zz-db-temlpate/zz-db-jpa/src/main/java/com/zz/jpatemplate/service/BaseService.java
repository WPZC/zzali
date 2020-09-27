package com.zz.jpatemplate.service;

import com.zz.domain.PageData;
import com.zz.domain.PageInfo;
import com.zz.domain.ParamInfo;
import com.zz.enums.Symbol;
import com.zz.jpatemplate.dao.BaseDao;
import com.zz.jpatemplate.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 基础查询模块
 * {@link BaseDao} 为扩展查询，继承自Jpa的 JpaRepositoryImplementation
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 16:04
 */
public abstract class BaseService<T,K extends BaseDao>{


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

    public K getK(){
        return k;
    }

    public T findById(Long Id){

        return (T) k.findById(Id).get();

    }

    /**
     * 根据主键ID删除
     * @param id
     */
    public void deleteById(Long id){
        k.deleteById(id);
    }

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
     * 分页查询(正序)
     * @param currentPage
     * @return
     */
    public PageData<T> findPages(Integer currentPage, Integer pagesize){
        return findByPages(currentPage,pagesize,Sort.Direction.ASC);
    }
    /**
     * 分页查询(正序)
     * @param currentPage
     * @return
     */
    public PageData<T> findPages(Integer currentPage, Integer pagesize,Sort.Direction sort){
        return findByPages(currentPage,pagesize,sort);
    }
    /**
     * 分页查询(正序)，条件分页查询
     * @param currentPage
     * @return
     */
    @Deprecated
    public PageData<T> findPages(Integer currentPage, Integer pagesize,Sort.Direction sort,Specification<T> specification){
        return findByPages(currentPage,pagesize,sort,specification);
    }

    /**
     * 条件分页查询
     * @param currentPage 页码
     * @param pagesize  一页显示多少条
     * @param sort 排序规则
     * @param specification 条件
     * @return
     */
    @Deprecated
    public PageData<T> findByPages(Integer currentPage, Integer pagesize,Sort.Direction sort,Specification<T> specification){
        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)

        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((currentPage-1)>=0?(currentPage-1):0, pagesize, sort, "id");

        Page<T> page = k.findAll(specification,pageable);
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

    @Deprecated
    public PageData<T> findByPages(Integer currentPage, Integer pagesize,Sort.Direction sort){
        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)

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
     * 根据pageInfo查询
     * @param pageInfo
     * @return
     */
    public PageData<T> findByPages(PageInfo pageInfo) throws Exception {
        //如果pageInfo为null，则创建默认pageInfo
        if(ObjectUtils.isEmpty(pageInfo)){
            pageInfo = new PageInfo();
        }
        //Sort.Direction是个枚举有ASC(升序)和DESC(降序)
        //定义sort
        Sort.Direction sort;
        //判断类型
        if(com.zz.enums.Sort.ASC == pageInfo.getSort()){
            sort = Sort.Direction.ASC;
        }else{
            sort = Sort.Direction.DESC;
        }
        //PageRequest继承于AbstractPageRequest并且实现了Pageable
        //获取PageRequest对象 index:页码 从0开始  size每页容量 sort排序方式 "id"->properties 以谁为准排序
        Pageable pageable = PageRequest.of((pageInfo.getCurrentPage()-1)>=0?(pageInfo.getCurrentPage()-1):0, pageInfo.getPagesize(), sort, pageInfo.getFiled());
        //定义page对象
        Page<T> page;
        //判断参数是否存在
        if(null == pageInfo.getParamInfos()||pageInfo.getParamInfos().size()==0){
            page = k.findAll(pageable);
        }else{
            //createSpencificatoin根据pageInfo创建Spencificatoin条件对象
            page = k.findAll(createSpecification(pageInfo.getParamInfos()), pageable);
        }
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
    @Deprecated
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
     * 不分页条件查询
     * @param specification 条件
     * @return
     */
    public List<T> getNoPageList(Specification<T> specification){

        return k.findAll(specification);

    }

    /**
     * 获取Specification条件(单符号)
     * maps为参数集，key为字段，value为值集
     * maps的value是list，list第一个为符号位，所以查询的时候会忽略第一个未位
     * eq:全等
     * nq:不等
     * like:模糊查询
     * notlike:非模糊查询
     * @param maps
     * @return
     */
    public Specification<T> createSpecification(HashMap<String, List<Object>> maps, Symbol symbol) {

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
                            case EQ:
                                //等于
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.equal(root.get(key), stro));
                                    }
                                });
                                break;
                            case NQ:
                                //不等于
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.notEqual(root.get(key), stro));
                                    }
                                });
                                break;
                            case LIKE:
                                //like
                                objects.stream().forEach(o -> {
                                    if(null!=o&&!o.equals("")){
                                        String stro = (String) o;
                                        predicates.add(criteriaBuilder.like(root.get(key), "%"+stro+"%"));
                                    }
                                });
                                break;
                            case NOTLIKE:
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

    /**
     * 获取Specification条件(多符号)
     * maps为参数集，key为字段，value为值集
     * maps的value是list，list第一个为符号位，所以查询的时候会忽略第一个未位
     * eq:全等
     * nq:不等
     * like:模糊查询
     * notlike:非模糊查询
     * @param pageInfos
     * @return
     */
    public Specification<T> createSpecification(List<ParamInfo> pageInfos) throws Exception{

        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (ParamInfo param:pageInfos) {
                    //循环map记性验证
                    List<Object> objects = param.getParams();
                    //符号
                    Symbol symbol  = param.getSymbol();
                    //取出后移除第一个
                    //objects.remove(0);
                    switch (symbol){
                        case EQ:
                            //等于
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    String stro = (String) o;
                                    predicates.add(criteriaBuilder.equal(root.get(param.getField()), stro));
                                }
                            });
                            break;
                        case NQ:
                            //不等于
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    String stro = (String) o;
                                    predicates.add(criteriaBuilder.notEqual(root.get(param.getField()), stro));
                                }
                            });
                            break;
                        case LIKE:
                            //like
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    String stro = (String) o;
                                    predicates.add(criteriaBuilder.like(root.get(param.getField()), "%"+stro+"%"));
                                }
                            });
                            break;
                        case NOTLIKE:
                            //nolike
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    String stro = (String) o;
                                    predicates.add(criteriaBuilder.notLike(root.get(param.getField()), "%"+stro+"%"));
                                }
                            });
                            break;
                        case BETWEEN:
                            //nolike
                            if(objects.size()!=2){
                                throw  new RuntimeException("BETWEEN 参数(objects)数量不为2,请检查参数是否正确！");
                            }

                            //将objects转为日期对象
                            try {
                                Date d1;
                                Date d2;
                                String o1 = (String) objects.get(0);
                                String o2 = (String) objects.get(1);
                                if(o1.length()>10){
                                    d1 = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.parse(o1);
                                }else{
                                    d1 = DateUtils.FORMAT_YYYY_MM_DD.parse(o1);
                                }
                                if(o2.length()>10){
                                    d2 = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS.parse(o2);
                                }else{
                                    d2 = DateUtils.FORMAT_YYYY_MM_DD.parse(o2);
                                }
                                predicates.add(criteriaBuilder.between(root.get(param.getField()),d1,d2));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            break;
                        default:
                            new RuntimeException("没有对比到符号位，请使用别的方法");
                    }
                }
                System.out.println("predicates:"+predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return specification;
    }


}
