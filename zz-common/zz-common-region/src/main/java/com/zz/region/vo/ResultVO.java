package com.zz.region.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    //10成功，11,失败，20需要登陆  30无权限等等
    private Integer code;
    private String msg;
    private T data;
}