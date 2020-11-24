package com.zz.nettyserver.cache;

import com.zz.nettyserver.cache.domain.NettyClientCache;
import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * 客户端缓存列表
 * @author wqy
 * @version 1.0
 * @date 2020/11/17 10:34
 */
public interface ClientCache {

    //以ctx上下文为key
    HashMap<Channel, NettyClientCache> NETTY_CTX_MAP = new HashMap<>();

}
