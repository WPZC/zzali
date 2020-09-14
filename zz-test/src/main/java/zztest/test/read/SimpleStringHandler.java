package zztest.test.read;

import com.zz.nettyserver.handler.ReadHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 13:51
 */
public class SimpleStringHandler extends ReadHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("SimpleServerHandler:"+msg);
        ctx.channel().writeAndFlush(msg);
    }
}
