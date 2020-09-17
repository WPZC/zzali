package com.zz.domain.authority;

import com.zz.domain.BaseDoMain;
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
public class RoleMenu  extends BaseDoMain {

	@Id
	private Long id;

	private Long rId;

	private Long mId;

}
