package zztest.test.read;

import com.zz.nettyserver.handler.ReadHandler;
import io.netty.channel.ChannelHandlerContext;
import zztest.test.TestEntity;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 15:11
 */
public class SimpleTestEntityHandler extends ReadHandler<TestEntity> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestEntity msg) throws Exception {
        System.out.println(msg.getAge());
        System.out.println(msg.getName());
        ctx.channel().writeAndFlush(msg);
    }
}
