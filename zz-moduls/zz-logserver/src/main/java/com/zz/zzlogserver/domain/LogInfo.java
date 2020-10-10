package com.zz.zzlogserver.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 日志信息
 * @author wqy
 * @version 1.0
 * @date 2020/9/29 14:53
 */
@Entity
@Data
@ApiModel(value = "日志信息")
public class LogInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "id",name = "id",dataType = "Long")
    @Column(name = "u_id")
    private Long uId;

    /**
     * 用户名
     */
    @Column(name = "u_name")
    private String uName;

    /**
     * 方法名
     */
    @Column(name = "f_name")
    private String fName;

    /**
     * 参数
     */
    @Column(name = "params")
    private String params;

    /**
     * 是否成功
     */
    @Column(name = "is_success")
    private Long isSuccess;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Date operationTime;






}
