package com.zz.nettyserver.cache.domain;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * client通道
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 11:53
 */
@Slf4j
@Builder
@Data
public class NettyClientCache {

    //服务名
    private String name;
    //通道控制权
    private Channel channel;
    //更新时间
    private Long updateTime;

    /**
     * 关闭
     * @return
     */
    public boolean canel(){
        try {
            //关闭通道
            this.channel.close();
            log.info(name+":++++++++++++++++++++++++++++客户端通道关闭++++++++++++++++++++++++++++:"+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
