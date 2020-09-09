package com.zz.zzsystemapi.service;

import com.zz.region.ServiceNameConstants;
import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.factory.RemoteMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 菜单fegin  （调用指定的数据源）
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 16:31
 */
@FeignClient(contextId = "menuMangementFegin", value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteMenuFallbackFactory.class)
public interface MenuMangementFegin {


}
