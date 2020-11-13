package com.zz.domain;

import com.zz.enums.Operation;
import lombok.Data;

import java.util.UUID;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/11/10 13:35
 */
@Data
public class OperationVo {

    private String code;

    private Operation operation;

    public OperationVo(Operation operation){
        this.code = UUID.randomUUID().toString();
        this.operation = operation;
    }

}
