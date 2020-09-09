package com.zz.region.domain.authority;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-06-09 
 */
@Entity
@Data
public class RoleMenu  {

	@Id
	private Long id;

	private Long rId;

	private Long mId;

}
