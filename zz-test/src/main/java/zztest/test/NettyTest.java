package zztest.test;

import com.zz.nettyserver.server.NettyServer;
import com.zz.region.methods.thread.ThreadFactory;
import io.netty.channel.ChannelOption;
import zztest.test.encode.PacketTestEntityEcoder;
import zztest.test.decode.PacketServerDecode;
import zztest.test.encode.PcaketStringEcoder;
import zztest.test.logic.LogicServerHanlder;
import zztest.test.read.SimpleStringHandler;
import zztest.test.read.SimpleTestEntityHandler;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 13:51
 */
public class NettyTest {

    public static void main(String[] args) throws InterruptedException {
//        NettyServer.bulider()
//                .serverBootstrap()
//                .addHandler(new DefaultServerHandler())
//                .bulid().start(5555);


        ThreadFactory.threadPoolExecutorTask(new Runnable() {
            @Override
            public void run() {
                try {
                    NettyServer.
                            bulider()
                            .option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE)
                            .childOption(ChannelOption.SO_KEEPALIVE, true)
                            .addHandler(PacketServerDecode.class)
                            .addHandler(LogicServerHanlder.class)
                            .addHandler(SimpleStringHandler.class)
                            .addHandler(SimpleTestEntityHandler.class)
                            .addHandler(PcaketStringEcoder.class)
                            .addHandler(PacketTestEntityEcoder.class)
                            .bulid()
                            .start(5555, "测试");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


//        Thread.sleep(20000);
//        System.out.println("执行关闭");
//        NettyServer.NETTY_CACHE_HASH_MAP.get("测试").canel();
    }

}
