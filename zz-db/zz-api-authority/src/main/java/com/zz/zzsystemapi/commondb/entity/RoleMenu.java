package com.zz.zzsystemapi.commondb.entity;

import com.zz.jpatemplate.domain.BaseDoMain;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-06-09 
 */
@Data
@Entity
@Table ( name ="role_menu" )
public class RoleMenu extends BaseDoMain {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "r_id" )
	private Long rId;

   	@Column(name = "m_id" )
	private Long mId;

}
