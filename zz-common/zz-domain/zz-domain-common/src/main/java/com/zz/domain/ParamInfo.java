package com.zz.domain;

import com.sun.org.apache.regexp.internal.RE;
import com.zz.enums.Operation;
import com.zz.enums.Symbol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 查询参数
 * @author wqy
 * @version 1.0
 * @date 2020/9/27 14:01
 */
@ApiModel(value = "查询参数")
public class ParamInfo {

    /**
     * 字段名
     */
    @ApiModelProperty(value = "字段名",name = "field",dataType = "String")
    private String field;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数（可多个参数）",name = "params",dataType = "List")
    private List<Object> params;

    /**
     * 符号
     */
    @ApiModelProperty(value = "符号（查询符号，默认为EQ）",name = "symbol",dataType = "Symbol")
    private Symbol symbol = Symbol.EQ;

    /**
     * 补足前字符
     */
    @ApiModelProperty(value = "备用字段，用于补足间接查询条件",name = "symbol",dataType = "Symbol")
    private String tsBefore;

    /**
     * 补足后字符
     */
    @ApiModelProperty(value = "备用字段，用于补足间接查询条件",name = "symbol",dataType = "Symbol")
    private String tsAfter;

    /**
     * 特殊条件,只有createSpecification存在对Operation进行处理的才能生效
     */
    private Map<Operation,List<ParamInfo>> operationMap;

//    public static Bulider bulider(){
//        return new Bulider();
//    }

//    /**
//     * 构建器
//     */
//    static class Bulider{
//
//        ParamInfo paramInfo;
//
//        public Bulider(){
//            this.paramInfo = new ParamInfo();
//        }
//        public Bulider field(String field){
//            this.paramInfo.field = field;
//            return this;
//        }
//        public Bulider params(List<Object> params){
//            this.paramInfo.params = params;
//            return this;
//        }
//        public Bulider symbol(Symbol symbol){
//            this.paramInfo.symbol = symbol;
//            return this;
//        }
//        public ParamInfo bulid(){
//            return this.paramInfo;
//        }
//
//    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        if(StringUtils.isEmpty(field)){
            this.field = "id";
        }else{
            this.field = field;
        }
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * 如果symbol为空或null
     * set默认值
     * @param symbol
     */
    public void setSymbol(Symbol symbol) {

        if(StringUtils.isEmpty(symbol)){
            this.symbol = Symbol.EQ;
        }else{
            this.symbol = symbol;
        }
    }

    public String getTsBefore() {
        return tsBefore;
    }

    public void setTsBefore(String tsBefore) {
        this.tsBefore = tsBefore;
    }

    public String getTsAfter() {
        return tsAfter;
    }

    public void setTsAfter(String tsAfter) {
        this.tsAfter = tsAfter;
    }
}
