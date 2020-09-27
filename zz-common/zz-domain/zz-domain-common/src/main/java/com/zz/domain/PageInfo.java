package com.zz.domain;

import com.zz.enums.Sort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息，当参数传入
 * @author wqy
 * @version 1.0
 * @date 2020/9/27 9:34
 */
@ApiModel(value = "分页信息",description = "{\"currentPage\":1,\"sort\":\"DESC\",\"params\":{\"id\":[\"参数1\"]},\"filed\":\"id\",\"symbol\":\"EQ\",\"symbols\":[\"EQ\",\"LIKE\"]}")
public class PageInfo {

    //页码
    @ApiModelProperty(value = "页码(默认1)",name = "currentPage",dataType = "Integer",required = true)
    private Integer currentPage = 1;
    //一页显示条数
    @ApiModelProperty(value = "一页显示条数(默认10)",name = "pagesize",dataType = "Integer",required = true)
    private Integer pagesize = 10;
    //排序规则
    @ApiModelProperty(value = "排序规则(ASC(正序),DESC(倒序))",name = "sort",dataType = "Sort",required = true)
    private Sort sort = Sort.DESC;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段(默认ID)",name = "filed",dataType = "String",required = true)
    private String filed = "id";


    @ApiModelProperty(value = "参数集",name = "paramInfos",dataType = "List",hidden = false)
    private List<ParamInfo> paramInfos;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if(StringUtils.isEmpty(currentPage)){
            this.currentPage = 1;
        }else{
            this.currentPage = currentPage;
        }

    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        if(StringUtils.isEmpty(pagesize)) {
            this.pagesize = 10;
        }else{
            this.pagesize = pagesize;
        }

    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        if(StringUtils.isEmpty(sort)){
            this.sort = Sort.DESC;
        }else{
            this.sort = sort;
        }
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        if(StringUtils.isEmpty(filed)){
            this.filed = "id";
        }else{
            this.filed = filed;
        }

    }

    public List<ParamInfo> getParamInfos() {
        return paramInfos;
    }

    public void setParamInfos(List<ParamInfo> paramInfos) {
        this.paramInfos = paramInfos;
//        if(null==paramInfos||paramInfos.size()==0){
//            this.paramInfos = new ArrayList<>();
//            this.paramInfos.add(new ParamInfo());
//        }else{
//            this.paramInfos = paramInfos;
//        }
    }
}
