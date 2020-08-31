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
@Table( name ="menu" )
public class Menu extends BaseDoMain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 菜单编号
	 */
   	@Column(name = "menu_code" )
	private String menuCode;

	/**
	 * 菜单名称
	 */
   	@Column(name = "menu_name" )
	private String menuName;

	/**
	 * 路由
	 */
	@Column(name = "router" )
	private String router;

	/**
	 * 图标地址
	 */
	@Column(name = "imgsrc" )
	private String imgsrc;


}
