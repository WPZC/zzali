package com.zz.jpatemplate.service;

import com.zz.domain.BaseDoMain;
import com.zz.domain.PageData;
import com.zz.domain.PageInfo;
import com.zz.domain.ParamInfo;
import com.zz.enums.Operation;
import com.zz.enums.Symbol;
import com.zz.interfaces.ParamInfoI;
import com.zz.jpatemplate.dao.BaseDao;
import com.zz.jpatemplate.interfaces.ParamComplex;
import com.zz.jpatemplate.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 基础查询模块
 * {@link BaseDao} 为扩展查询，继承自Jpa的 JpaRepositoryImplementation
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 16:04
 */
public class BaseService<T extends BaseDoMain,K extends BaseDao> implements BaseServiceI<T,K>{


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
     * 获取资源库
     * @return
     */
    @Override
    public K getK(){
        return k;
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public T findById(Long id){

        return (T) k.findById(id).get();

    }

    /**
     * 根据主键ID删除
     * @param id
     */
    @Override
    public void deleteById(Long id){
        k.deleteById(id);
    }

    /**
     * 修改
     * @param t
     */
    @Override
    public void updateData(T t){

        //对像判断
        Assert.notNull(t,"对象不能为空");
        //id非空判断
        Assert.notNull(t.getId(),"修改ID不能为空");
        //判断是否保存成功
        Assert.notNull(k.save(t),"操作失败");
    }
    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    @Override
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
    @Override
    public PageData<T> findPages(Integer currentPage, Integer pagesize){
        return findByPages(currentPage,pagesize,Sort.Direction.ASC);
    }
    /**
     * 分页查询(正序)
     * @param currentPage
     * @return
     */
    @Override
    public PageData<T> findPages(Integer currentPage, Integer pagesize, Sort.Direction sort){
        return findByPages(currentPage,pagesize,sort);
    }
    /**
     * 分页查询(正序)，条件分页查询
     * @param currentPage
     * @return
     */
    @Override
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
    @Override
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

    /**
     * 分页查询
     * @param currentPage
     * @param pagesize
     * @param sort
     * @return
     */
    @Override
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
    @Override
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
     * 根据Specification和pageInfo查询，该方法忽略pageinfo中的参数信息
     * @param pageInfo
     * @return
     */
    public PageData<T> findByPages(Specification<T> specification,PageInfo pageInfo) throws Exception {
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
        page = k.findAll(specification,pageable);
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
    @Override
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
    @Override
    public List<T> findNoPageList(Specification<T> specification){

        return k.findAll(specification);

    }
    /**
     * 不分页条件查询
     * @param specification 条件
     * @param direction     排序方式
     * @param perproties    根据哪个字段排序
     * @return
     */
    public List<T> findNoPageList(Specification<T> specification,Sort.Direction direction,String perproties){

        Sort sort = Sort.by(direction,perproties);

        return k.findAll(specification,sort);

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
    @Deprecated
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
     * 简单条件查询
     * 获取Specification条件(多符号)
     * maps为参数集，key为字段，value为值集
     * maps的value是list，list第一个为符号位，所以查询的时候会忽略第一个未位
     * eq:全等
     * nq:不等
     * like:模糊查询
     * notlike:非模糊查询
     * @param paramInfos
     * @return
     */
    public Specification<T> createSpecification(List<ParamInfo> paramInfos) throws Exception{

        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (ParamInfo param:paramInfos) {
                    System.out.println(param.hashCode());
                    //循环map记性验证
                    List<Object> objects = param.getParams();
                    //符号
                    Symbol symbol  = param.getSymbol();
                    switch (symbol){
                        case EQ:
                            //等于
                            objects.stream().forEach(o -> {
                                if(null!=o && !o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.equal(root.get(param.getField()), o));
                                }
                            });
                            break;
                        case NQ:
                            //不等于
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.notEqual(root.get(param.getField()), o));
                                }
                            });
                            break;
                        case LIKE:
                            //like
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.like(root.get(param.getField()), "%"+o+"%"));
                                }
                            });
                            break;
                        case LIKERIGHT:
                            //右模糊
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.like(root.get(param.getField()), param.getTsBefore() + o + param.getTsAfter() + "%"));
                                }
                            });
                            break;
                        case LIKELEFT:
                            //左模糊
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.like(root.get(param.getField()), "%" + param.getTsBefore() + o + param.getTsAfter()));
                                }
                            });
                            break;
                        case NOTLIKE:
                            //nolike
                            objects.stream().forEach(o -> {
                                if(null!=o&&!o.equals("")){
                                    //String stro = (String) o;
                                    predicates.add(criteriaBuilder.notLike(root.get(param.getField()), "%"+o+"%"));
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
                if(predicates.size()==0){
                    return null;
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return specification;
    }

    /*----------------------------------特殊方法去---------------------------------------------------*/
    /**
     * 根据pageInfo查询
     * @param pageInfo
     * @return
     */
    public PageData<T> findByPagesRole(Specification s,PageInfo pageInfo) throws Exception {
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
        page = k.findAll(s, pageable);
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
}
