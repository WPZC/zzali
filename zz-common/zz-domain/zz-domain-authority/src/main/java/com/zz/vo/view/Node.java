package com.zz.vo.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * tree节点
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 16:04
 */
@Data
@ApiModel(value = "菜单节点")
public class Node {

    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;
    @ApiModelProperty(value = "标签",name = "label",dataType = "String")
    private String label;
    @ApiModelProperty(value = "菜单编码",name = "code",dataType = "String")
    private String code;
    @ApiModelProperty(value = "子节点",name = "children",dataType = "List<Node>")
    private List<Node> children;

}
