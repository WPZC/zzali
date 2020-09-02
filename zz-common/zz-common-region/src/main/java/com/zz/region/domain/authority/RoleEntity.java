package com.zz.region.domain.authority;
 
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-25 
 */
@Data
@ApiModel(value = "角色实体")
public class RoleEntity  {
 

	@ApiModelProperty(value = "id",name = "id",dataType = "Long")
	private Long id;
	@ApiModelProperty(value = "角色名称",name = "name",dataType = "String")
	private String name;
	@ApiModelProperty(value = "描述",name = "description",dataType = "String")
	private String description;
 
 
}