package com.zz.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "返回总体")
public class ResultVO<T> {
    //10成功，11,失败，20需要登陆  30无权限等等
    @ApiModelProperty(value = "状态码:10成功，11,失败，20需要登陆  30无权限",name = "code",dataType = "Integer")
    private Integer code;
    @ApiModelProperty(value = "返回提示消息",name = "msg",dataType = "String")
    private String msg;
    @ApiModelProperty(value = "数据体",name = "data",dataType = "T")
    private T data;
}