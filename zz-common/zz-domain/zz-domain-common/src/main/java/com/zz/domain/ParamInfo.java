package com.zz.domain;

import com.zz.enums.Symbol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.util.List;

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
}
