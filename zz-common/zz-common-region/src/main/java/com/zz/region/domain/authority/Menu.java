package com.zz.region.domain.authority;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-06-09 
 */
@Builder
@Data
public class Menu  {

	private Long id;

	/**
	 * 菜单编号
	 */
	private String menuCode;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 路由
	 */
	private String router;

	/**
	 * 图标地址
	 */
	private String imgsrc;

	/**
	 * 子菜单序号
	 */
	private String index;

	@Tolerate
	public Menu (){}


}
