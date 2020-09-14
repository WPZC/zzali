package com.zz.nettyserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 读 逻辑处理
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 9:59
 */
public abstract class ReadHandler<T> extends SimpleChannelInboundHandler<T> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        //此处写读的逻辑处理，转成T以后调用ctx.channel().writeAndFlush(msg),写入通道，ctx会根据数据类型寻找下一个处理
        ctx.channel().writeAndFlush(msg);
    }
}
