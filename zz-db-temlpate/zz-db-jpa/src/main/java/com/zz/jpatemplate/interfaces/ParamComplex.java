package com.zz.jpatemplate.interfaces;

import com.sun.istack.NotNull;
import com.zz.domain.OperationVo;
import com.zz.domain.ParamInfo;
import com.zz.enums.Operation;
import com.zz.enums.Symbol;
import com.zz.jpatemplate.utils.DateUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.*;

/**
 * 复杂条件构建器
 * @author wqy
 * @version 1.0
 * @date 2020/11/9 15:11
 */
public interface ParamComplex {

    /**
     * 构建器
     * @return
     */
    static Bulider bulider(){
        return new Bulider();
    }

    /**
     * 构建类
     *
     * 通过Operation作为分界线，Operation之前的为一组（也就是一个括号）
     *
     * paramMaps没经过一次Operation就会清空，并将老值与Operation一起传入all，
     *
     * all在build的时候会根据Operation进行转换处理，all中的ParamInfo为参数体
     *
     * 参数体的详情见{@link ParamInfo}
     *
     * 思路：ParamInfo可以理解为参数单元，多个参数单元组成一个参数集，
     *       Operation作为参数单元与参数单元，或者参数单元与参数集的中间条件
     *
     *       比如：where a=1 and (b=2 or c=3)
     *
     *       a=1为参数单元，(b=2 or c=3)为参数集
     *
     *       and和or为中间条件。
     *
     *      构建实例，比如 p1为eq，p2为eq，p3为eq，p4为like
     *      p1，p2为逻辑且(and)，p3，p4为逻辑或(or)
     *      通过此构建出来的where为where p1=? and p2=? and (p3=? or p4=?)
     *
     *      Specification<Rhoutlet> specification = ParamComplex
     *                 .bulider()
     *                 .params(p1)
     *                 .params(p2)
     *                 .operation(Operation.AND)
     *                 .params(p3)
     *                 .params(p4)
     *                 .operation(Operation.OR)
     *                 .bulid();
     *
     *
     */
    class Bulider<T>{

        /**
         * 参数级
         */
        List<ParamInfo> paramMaps = new ArrayList<>();

        /**
         * 截取合集
         */
        Map<OperationVo,List<ParamInfo>> all = new LinkedHashMap<>();

        /**
         * 参数构建
         * @param paramInfo
         * @return
         */
        @NotNull
        public Bulider<T> params(ParamInfo paramInfo){
            paramMaps.add(paramInfo);
            return this;
        }

        /**
         * 设置结束符号
         * @param operation
         * @return
         */
        @NotNull
        public Bulider operation(Operation operation){
            all.put(new OperationVo(operation),this.paramMaps);
            this.paramMaps = new ArrayList<>();
            return this;
        }

        /**
         * 构建对象
         * @param <T>
         * @return
         */
        public <T> Specification<T> bulid(){

            //赋值，为了使用lamda
            final Map<OperationVo,List<ParamInfo>> all = this.all;

            /**
             * 核心
             * 核心构建Specification条件
             */
            Specification<T> specification = new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> r, CriteriaQuery<?> c, CriteriaBuilder cb) {
                    //存放结果集，让c根据ps构建where
                    final List<Predicate> ps = new ArrayList<>();
                    //遍历all
                    all.forEach((o,v)->{
                        //整合数据单元，根据ParamInfo生成对应的查询条件
                        List<Predicate> predicates = createPredicates(v,r,cb);
                        //如果predicates为0则跳过
                        if(predicates.size()!=0){
                            switch (o.getOperation()){
                                case OR:
                                    //处理or
                                    ps.add(cb.or(predicates.toArray(new Predicate[predicates.size()])));
                                    break;
                                case AND:
                                    //处理and
                                    ps.add(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                                    break;
                                default:
                                    System.out.println("---------------");
                            }
                        }

                    });
                    //根据ps创建where
                    return c.where(ps.toArray(new Predicate[ps.size()])).getRestriction();
                }
            };

            return specification;
        }

        /**
         * 整合ParamInfo参数，生成Predicate数据单元或者数据集
         * @param paramInfos
         * @param r
         * @param cb
         * @return
         */
        private List<Predicate> createPredicates(List<ParamInfo> paramInfos,Root r,CriteriaBuilder cb){

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
                                predicates.add(cb.equal(r.get(param.getField()), o));
                            }
                        });
                        break;
                    case NQ:
                        //不等于
                        objects.stream().forEach(o -> {
                            if(null!=o&&!o.equals("")){
                                //String stro = (String) o;
                                predicates.add(cb.notEqual(r.get(param.getField()), o));
                            }
                        });
                        break;
                    case LIKE:
                        //like
                        objects.stream().forEach(o -> {
                            if(null!=o&&!o.equals("")){
                                //String stro = (String) o;
                                predicates.add(cb.like(r.get(param.getField()), "%"+o+"%"));
                            }
                        });
                        break;
                    case LIKERIGHT:
                        //右模糊
                        objects.stream().forEach(o -> {
                            if(null!=o&&!o.equals("")){
                                //String stro = (String) o;
                                predicates.add(cb.like(r.get(param.getField()), (param.getTsBefore()==null?"":param.getTsBefore()) + o + (param.getTsAfter()==null?"":param.getTsAfter()) + "%"));
                            }
                        });
                        break;
                    case LIKELEFT:
                        //左模糊
                        objects.stream().forEach(o -> {
                            if(null!=o&&!o.equals("")){
                                //String stro = (String) o;
                                predicates.add(cb.like(r.get(param.getField()), "%" + (param.getTsBefore()==null?"":param.getTsBefore()) + o + (param.getTsAfter()==null?"":param.getTsAfter())));
                            }
                        });
                        break;
                    case NOTLIKE:
                        //nolike
                        objects.stream().forEach(o -> {
                            if(null!=o&&!o.equals("")){
                                //String stro = (String) o;
                                predicates.add(cb.notLike(r.get(param.getField()), "%"+o+"%"));
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
                            predicates.add(cb.between(r.get(param.getField()),d1,d2));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        new RuntimeException("没有对比到符号位，请使用别的方法");
                }
            }
            return predicates;
        }

    }

}
