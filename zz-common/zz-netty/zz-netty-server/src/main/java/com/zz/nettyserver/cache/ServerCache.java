package com.zz.nettyserver.cache;

import com.zz.nettyserver.cache.domain.NettyServerCache;

import java.util.HashMap;

/**
 * netty服务缓存
 * @author wqy
 * @version 1.0
 * @date 2020/11/17 10:34
 */
public interface ServerCache {

    /**
     * netty启动服务缓存
     */
    HashMap<String, NettyServerCache> NETTY_CACHE_MAP = new HashMap<>();

}
