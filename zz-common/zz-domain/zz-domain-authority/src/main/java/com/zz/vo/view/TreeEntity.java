package com.zz.vo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * tree返回实体
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 18:36
 */
@Builder
@Data
@ApiModel(value = "菜单Tree实体")
public class TreeEntity {

    /**
     * 节点
     */
    @ApiModelProperty(value = "Node节点",name = "nodes",dataType = "List<Node>")
    private List<Node> nodes;

    /**
     * 被选节点
     */
    @ApiModelProperty(value = "选中的节点列表",name = "checked",dataType = "List<Long>")
    private List<Long> checked;
}
