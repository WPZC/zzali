package zztest.test.logic;

import com.zz.nettyserver.handler.LogicHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 13:49
 */
public class LogicServerHanlder extends LogicHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(msg);

        super.channelRead(ctx, msg);
    }

    /**
     * 沟道不活跃，
     * 通道断开连接时执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道断开连接");
        ctx.channel().close();
    }
}
