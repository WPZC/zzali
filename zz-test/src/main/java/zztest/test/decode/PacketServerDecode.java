package zztest.test.decode;

import com.zz.nettyserver.handler.PacketDecoder;
import com.zz.nettyserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.StringUtil;
import zztest.test.TestEntity;

import java.util.List;

/**
 * @author  wqy
 * @date  2020/9/14 11:55
 * @version 1.0
 */
public class PacketServerDecode extends PacketDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("数据解码");
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        String cmd = HexByte.characterAi(StringUtil.toHexString(req),"HEXTOSTR");
        out.add(cmd);
        TestEntity entity = new TestEntity();
        entity.setAge("12");
        entity.setName("laowang");
        out.add(entity);
    }


}
