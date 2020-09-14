package com.zz.nettyserver.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * netty信息保存，用于关闭通道
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 11:53
 */
@Slf4j
@Builder
@Data
public class NettyCache {

    //服务名
    private String name;
    //通道控制权
    private ChannelFuture channelFuture;

    /**
     * 关闭
     * @return
     */
    public boolean canel(){
        try {
            //关闭通道
            this.channelFuture.channel().close();
            log.info(name+":++++++++++++++++++++++++++++服务结束，通道关闭++++++++++++++++++++++++++++:"+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
