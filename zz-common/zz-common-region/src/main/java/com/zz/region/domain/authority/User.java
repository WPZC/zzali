package com.zz.region.domain.authority;
 
 
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-05 
 */
//@Builder
@Data
@Entity
@ApiModel(value = "用户实体")
public class User {
 

	@Id
	@ApiModelProperty(value = "id",name = "id",dataType = "Long")
	private Long id;
 
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名",name = "username",dataType = "String")
	private String username;
 
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码",name = "password",dataType = "String")
	private String password;
 
	/**
	 * 保存时间
	 */
	@ApiModelProperty(value = "保存时间",name = "savetime",dataType = "Date",hidden = true)
	private Date savetime;
 
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号",name = "iphone",dataType = "String")
	private String iphone;
 
	/**
	 * 性别，1男，2女
	 */
	@ApiModelProperty(value = "性别(1男，2女)",name = "sex",dataType = "String")
	private Integer sex;
 
	/**
	 * 组织机构编号
	 */
	@ApiModelProperty(value = "组织机构编号",name = "organizationNum",dataType = "String",hidden = true)
	private String organizationNum;
 
	/**
	 * 组织机构名称
	 */
	@ApiModelProperty(value = "组织机构名称",name = "organizationName",dataType = "String",hidden = true)
	private String organizationName;

}