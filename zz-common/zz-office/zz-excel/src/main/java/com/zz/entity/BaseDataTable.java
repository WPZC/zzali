package com.zz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-06-17 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseDataTable{

	private Long id;

	/**
	 * 前10字段
	 */
	private String top10;

	/**
	 * 10-20字段(不含10)
	 */
	private String top20;

	/**
	 * 20-30字段(不含20)
	 */
	private String top30;

	private String top40;

	private String top50;

	private String top60;

	private String top70;

	private String top80;

	private String top90;

	private String top100;

	private Date saveTime;
}
