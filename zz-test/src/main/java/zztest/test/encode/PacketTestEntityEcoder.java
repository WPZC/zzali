package zztest.test.encode;

import com.zz.nettyserver.handler.PacketEcoder;
import com.zz.nettyserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import zztest.test.TestEntity;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 15:24
 */
public class PacketTestEntityEcoder extends PacketEcoder<TestEntity> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TestEntity msg, ByteBuf out) throws Exception {
        //写数据
        System.out.println("PacketEntityTest写数据");
        out.writeBytes(HexByte.hexStringToByte(HexByte.characterAi(msg.getAge()+"-"+msg.getName(),"STRTOHEX")));
    }
}
