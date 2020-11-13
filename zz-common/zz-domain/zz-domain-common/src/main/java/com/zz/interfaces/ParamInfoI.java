package com.zz.interfaces;

import com.zz.domain.ParamInfo;
import com.zz.enums.Symbol;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/9 9:36
 */
public interface ParamInfoI {

    /**
     * 构建器
     * @return
     */
    static Bulider bulider(){
        return new Bulider();
    }

    /**
     * 构建类
     */
    class Bulider{

        /**
         * 字段名
         */
        private String field;

        /**
         * 参数
         */
        private List<Object> params;

        /**
         * 符号
         */
        private Symbol symbol = Symbol.EQ;

        /**
         * 补足前字符
         */
        private String tsBefore;

        /**
         * 补足后字符
         */
        private String tsAfter;

        /**
         * 字段名
         */
        public Bulider field(String field){
            this.field = field;
            return this;
        }

        /**
         * 构建参数
         * @param params
         * @return
         */
        public Bulider params(List<Object> params){
            this.params = params;
            return this;
        }
        /**
         * 构建参数符号
         * @param symbol
         * @return
         */
        public Bulider symbol(Symbol symbol){
            this.symbol = symbol;
            return this;
        }
        /**
         * 补足前字符
         */
        public Bulider tsBefore(String tsBefore){
            this.tsBefore = tsBefore;
            return this;
        }
        /**
         * 补足后字符
         */
        public Bulider tsAfter(String tsAfter){
            this.tsAfter = tsAfter;
            return this;
        }

        /**
         * 构建
         * @return
         */
        public ParamInfo bulid(){

            ParamInfo paramInfo = new ParamInfo();
            paramInfo.setTsAfter(tsAfter);
            paramInfo.setTsBefore(tsBefore);
            paramInfo.setParams(params);
            paramInfo.setSymbol(symbol);
            paramInfo.setField(field);

            return paramInfo;

        }

    }

}
