package com.zz.region.vo.view;

import lombok.Data;

import java.util.List;

/**
 * tree节点
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 16:04
 */
@Data
public class Node {

    private Long id;

    private String label;

    private String code;

    private List<Node> children;

}
