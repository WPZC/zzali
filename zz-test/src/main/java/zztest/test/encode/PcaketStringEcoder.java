package zztest.test.encode;

import com.zz.nettyserver.handler.PacketEcoder;
import com.zz.nettyserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 13:53
 */
public class PcaketStringEcoder extends PacketEcoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        System.out.println("PcaketTest写数据");
        // 2. 写数据
        out.writeBytes(HexByte.hexStringToByte(HexByte.characterAi(msg,"STRTOHEX")));
    }
}
