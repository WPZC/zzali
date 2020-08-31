package com.zz.zzsystemapi.commondb.entity;


import com.zz.jpatemplate.domain.BaseDoMain;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-05 
 */
@Data
@Entity
@Table ( name ="user" )
public class UserEntity extends BaseDoMain {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;
 
	/**
	 * 用户名
	 */
   	@Column(name = "username" )
	private String username;
 
	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;
 
	/**
	 * 保存时间
	 */
   	@Column(name = "savetime" )
	private Date savetime;
 
	/**
	 * 手机号
	 */
   	@Column(name = "iphone" )
	private String iphone;
 
	/**
	 * 性别，1男，2女
	 */
   	@Column(name = "sex" )
	private Integer sex;
 
	/**
	 * 组织机构编号
	 */
   	@Column(name = "organization_num" )
	private String organizationNum;
 
	/**
	 * 组织机构名称
	 */
   	@Column(name = "organization_name" )
	private String organizationName;
}