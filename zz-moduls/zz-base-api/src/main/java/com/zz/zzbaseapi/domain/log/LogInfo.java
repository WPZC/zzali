package com.zz.zzbaseapi.domain.log;

import com.zz.security.utils.SecurityUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 日志信息
 * @author wqy
 * @version 1.0
 * @date 2020/9/29 14:53
 */
@Data
@ApiModel(value = "日志信息")
public class LogInfo {
    /**
     * 用户ID
     */
    private Long uId;

    /**
     * 用户名
     */
    private String uName = SecurityUtils.getUsername();

    /**
     * 方法名
     */
    private String fName = Thread.currentThread().getStackTrace()[2].getClassName()+"."+Thread.currentThread().getStackTrace()[2].getMethodName();

    /**
     * 参数
     */
    private String params;

    /**
     * 是否成功(0成功，1失败)
     */
    private Long isSuccess = 0L;

    /**
     * 操作时间
     */
    private Date operationTime = new Date();

    public LogInfo(String params){
        this.params = params;
    }







}
