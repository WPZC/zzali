package com.zz.nettyserver.handler;

import com.alibaba.fastjson.JSONObject;
import com.zz.nettyserver.handler.test.Aes;
import com.zz.nettyserver.handler.test.Md5;
import com.zz.nettyserver.handler.test.PostServer;
import com.zz.nettyserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.StringUtil;
import java.util.Date;

/**
 * 默认逻辑处理器
 * ChannelInboundHandlerAdapter为netty的逻辑处理器
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 9:13
 */
public class DefaultServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 信道登记，刚有连接连入的时候,
     * 只有刚连入的时候触发一次
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Welcome to connect:"+ctx.toString());
    }

    /**
     * 信道未注册，
     * 通道断开连接后执行，级别低于channelInactive
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("信道未注册");
        super.channelUnregistered(ctx);
    }

    /**
     * 沟道激活
     * 只有刚接入的时候触发一次
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("沟道激活");
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
    }

    /**
     * 沟道读
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String cmd = HexByte.characterAi(StringUtil.toHexString(req),"HEXTOSTR");
        System.out.println(new Date() + ": 服务端读到数据 -> " + cmd);


        //拿到图灵机器数据
        //图灵网站上的secret
        String secret = "e6b9c1e3b3e3dd90";//您的secret
        //图灵网站上的apiKey
        String apiKey = "d00e766d7b754c76aa5c22e206ca3bf6";//您的apiKey
        //待加密的json数据
        String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\"}";
        //获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        //生成密钥
        String keyParam = secret+timestamp+apiKey;
        String key = Md5.MD5(keyParam);

        //加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);

        //封装请求参数
        JSONObject json = new JSONObject();
        json.put("key", apiKey);
        json.put("timestamp", timestamp);
        json.put("data", data);
        //请求图灵api：返回的结果:json的字符串结果！
        String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");

        System.out.println(result);
        String str = result;

        byteBuf.writeBytes(HexByte.hexStringToByte(HexByte.characterAi(str,"STRTOHEX")));
        // 2. 写数据
        ctx.channel().writeAndFlush(byteBuf);


    }

    /**
     * 通道读取完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道读取完成");
    }

    /**
     * 已触发用户事件
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("已触发用户事件");
    }

    /**
     * 频道可写入性已更改
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("频道可写入性已更改");
    }

    /**
     * 异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常");
    }
}
