package com.zz.nettyserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * Netty粘包拆包
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 11:23
 */
public abstract class Unpack {

    private ChannelHandler channelHandler;

    /**
     * 构建定长拆包器
     * @param frameLength
     * @return
     */
    public ChannelHandler fixedLengthFrameDecoder(int frameLength){
        return new FixedLengthFrameDecoder(frameLength);
    }
    /**
     * 构建行拆包器
     * @param maxLength 一帧的最大长度
     * @return
     */
    public ChannelHandler lineBasedFrameDecoder(int maxLength){
        return new LineBasedFrameDecoder(maxLength);
    }


    /**
     * 构建分隔符拆包器
     * @param maxLength 一帧的最大长度
     * @param separator 分隔符
     * @return
     */
    public ChannelHandler delimiterBasedFrameDecoder(int maxLength,String separator){
        ByteBuf delimiter = Unpooled.copiedBuffer(separator.getBytes());
        return new DelimiterBasedFrameDecoder(maxLength,delimiter);
    }

    /**
     * 构建分隔符拆包器
     * @param maxLength 一帧的最大长度
     * @param lengthFieldOffset 长度域偏移量，就是长度域的位置从第几个字节开始
     * @param lengthFieldLength 长度域的字节数
     * @return
     */
    public ChannelHandler lengthFieldBasedFrameDecoder(int maxLength,int lengthFieldOffset,int lengthFieldLength){

        return new LengthFieldBasedFrameDecoder(maxLength,lengthFieldOffset,lengthFieldLength);
    }



}
