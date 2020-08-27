package com.zz.region.domain.authority;

import lombok.Data;

/**
 * @Description  
 * @Author  WQY
 * @Date 2020-05-28 
 */
@Data
public class UserRoleEntity {

	private Long id;

	private Long uId;

	private Long rId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUId() {
		return this.uId;
	}

	public void setUId(Long uId) {
		this.uId = uId;
	}

	public Long getRId() {
		return this.rId;
	}

	public void setRId(Long rId) {
		this.rId = rId;
	}

}
