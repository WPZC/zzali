package com.zz.region.vo.view;

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
public class TreeEntity {

    /**
     * 节点
     */
    private List<Node> nodes;

    /**
     * 被选节点
     */
    private List<Long> checked;
}
