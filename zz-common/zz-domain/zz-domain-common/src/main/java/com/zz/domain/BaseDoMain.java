package com.zz.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 15:42
 */
@Data
public class BaseDoMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;

}
