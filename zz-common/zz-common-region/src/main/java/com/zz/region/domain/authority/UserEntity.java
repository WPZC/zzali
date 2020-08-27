package com.zz.region.domain.authority;
 
 
import lombok.Data;

import java.util.Date;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-05 
 */
//@Builder
@Data
public class UserEntity {
 

	private Long id;
 
	/**
	 * 用户名
	 */
	private String username;
 
	/**
	 * 密码
	 */
	private String password;
 
	/**
	 * 保存时间
	 */
	private Date savetime;
 
	/**
	 * 手机号
	 */
	private String iphone;
 
	/**
	 * 性别，1男，2女
	 */
	private Integer sex;
 
	/**
	 * 组织机构编号
	 */
	private String organizationNum;
 
	/**
	 * 组织机构名称
	 */
	private String organizationName;

}