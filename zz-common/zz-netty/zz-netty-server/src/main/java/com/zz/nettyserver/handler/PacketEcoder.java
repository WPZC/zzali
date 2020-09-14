package com.zz.nettyserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 响应编码器,
 * 重写encode
 * @author wqy
 * @version 1.0
 * @date 2020/9/14 11:52
 */
public abstract class PacketEcoder<T> extends MessageToByteEncoder<T> {
    @Override
    protected void encode(ChannelHandlerContext ctx, T msg, ByteBuf out) throws Exception {
        //重写次方法
        //此处往out中写数据，不建议主动调用ctx，如果要调用ctx在此处发送数据，需重新创建ByteBuf
    }
}
