package com.zz.zzsystemapi.commondb.entity;

import com.zz.jpatemplate.domain.BaseDoMain;
import lombok.Data;

import javax.persistence.*;


/**
 * @Description  
 * @Author  WQY
 * @Date 2020-05-28 
 */
@Data
@Entity
@Table( name ="user_role" )
public class UserRoleEntity extends BaseDoMain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "u_id" )
	private Long uId;

   	@Column(name = "r_id" )
	private Long rId;

}
