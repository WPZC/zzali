package com.zz.nettyserver.server;

import com.zz.nettyserver.handler.DefaultServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * netty构建启动服务
 * @author wqy
 * @version 1.0
 * @date 2020/9/12 8:53
 */
public interface NettyServer {

    /**
     * netty启动服务缓存
     */
    HashMap<String,NettyCache> NETTY_CACHE_HASH_MAP = new HashMap<>();

    /**
     * 构建器
     * @return
     */
    static Bulider bulider(){
        return new Bulider();
    }
    /**
     * 构建器
     */
    @Slf4j
    class Bulider{
        //监听端口，接受新连接的线程组
        private NioEventLoopGroup bossGroup;
        //处理每一条连接的数据读写的线程组
        private NioEventLoopGroup workerGroup;
        //引导类
        private ServerBootstrap serverBootstrap;
        //为每一条连接指定自定义属性
        private AttributeKey<Object> attributeKeyClient = AttributeKey.newInstance("clientKey");
        private AttributeKey<Object> attributeKeyServer = AttributeKey.newInstance("serverKey");
        //给每一条连接指定一些tcp底层相关的属性
        private final Map<ChannelOption<?>, Object> childOptions = new LinkedHashMap();
        //为服务端channel(NioServerSocketChannel)设置一些属性
        private final Map<ChannelOption<?>, Object> options = new LinkedHashMap();
        //逻辑handler
        List<Class<ChannelHandler>> channelHandlers = new ArrayList<>();
        
        //粘包拆包器
        private ChannelHandler stickyPackageUnpacking = null;

        public Bulider(){
            this.bossGroup = new NioEventLoopGroup();
            this.workerGroup = new NioEventLoopGroup();
            this.serverBootstrap = new ServerBootstrap();
        }

        /**
         * 构建引导类
         * @return
         */
//        public Bulider serverBootstrap(){
//            this.serverBootstrap = new ServerBootstrap();
//            return this;
//        }



        /**
         * 为服务端channel(NioServerSocketChannel)指定自定义的属性
         * @return
         */
        public Bulider attr(String key){
            this.attributeKeyServer = AttributeKey.newInstance(key);
            return this;
        }

        /**
         * 为每一条连接指定自定义属性
         * @return
         */
        public Bulider childAttr(String key){
            this.attributeKeyClient = AttributeKey.newInstance(key);
            return this;
        }

        /**
         * 为服务端channel(NioServerSocketChannel)设置一些属性
         * @return
         */
        public <T> Bulider option(ChannelOption<T> option, T value) {
            ObjectUtil.checkNotNull(option, "option");
            synchronized(this.options) {
                if (value == null) {
                    this.options.remove(option);
                } else {
                    this.options.put(option, value);
                }
            }

            return this;
        }

        /**
         * 给每一条连接指定一些tcp底层相关的属性
         * @return
         */
        public <T> Bulider childOption(ChannelOption childOption, T value) {
            ObjectUtil.checkNotNull(childOption, "childOption");
            synchronized(this.childOptions) {
                if (value == null) {
                    this.childOptions.remove(childOption);
                } else {
                    this.childOptions.put(childOption, value);
                }

                return this;
            }
        }

        /**
         * 添加自定义处理
         * @param handler
         * @return
         */
        public Bulider addHandler(Class handler){
            this.channelHandlers.add(handler);
            return this;
        }

        /**
         * 添加拆包粘包器
         * @param stickyPackageUnpacking
         * @return
         */
        public Bulider addStickyPackageUnpacking(ChannelHandler stickyPackageUnpacking){
            this.stickyPackageUnpacking = stickyPackageUnpacking;
            return this;
        }

        /**
         * 构建自定义处理器，按顺序构建,初始通道，此处改为c.newInstance(),
         * 是因我每一个连接都会对应一个初始化
         * @return
         */
        private ChannelInitializer buliderHandler(){
            return new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    if(ObjectUtils.isEmpty(channelHandlers)){
                        ch.pipeline().addLast(new DefaultServerHandler());
                    }else{
                        //判断是否含有粘包拆包器，有的话加在第一个
                        if (stickyPackageUnpacking!=null){
                            ch.pipeline().addLast(stickyPackageUnpacking);
                        }
                        channelHandlers.stream().forEach(c->{
                            try {
                                ch.pipeline().addLast(c.newInstance());
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                }
            };
        }

        /**
         * 构建启动对象
         * @return
         */
        public Start bulid(){

            this.serverBootstrap
                    //配置两大线程组，定型引导类的线程模型
                    .group(this.bossGroup,this.workerGroup)
                    //指定IO模型为NIO
                    .channel(NioServerSocketChannel.class)
                    //为服务端channel(NioServerSocketChannel)指定自定义的属性
                    .attr(this.attributeKeyServer,"nettyServer")
                    //为每一条连接指定自定义属性
                    .childAttr(this.attributeKeyClient,"clientValue");
            //为服务端channel(NioServerSocketChannel)设置一些属性，设置临时存放已完成三次握手请求的队列最大值
            for (ChannelOption c:options.keySet()){
                this.serverBootstrap.option(c,options.get(c));
            }
            //给每一条连接指定一些tcp底层相关的属性，开启tcp底层心跳机制
            for (ChannelOption c:childOptions.keySet()){
                this.serverBootstrap.childOption(c,childOptions.get(c));
            }
            //泛型参数NioServerSocketChannel，Netty对NIO类型的连接的抽象
            this.serverBootstrap.childHandler(this.buliderHandler());

            return new Start(this.serverBootstrap,this.bossGroup,this.workerGroup);
        }

    }

    /**
     * 启动类
     */
    @Slf4j
    class Start{

        //监听端口，接受新连接的线程组
        private NioEventLoopGroup bossGroup;
        //处理每一条连接的数据读写的线程组
        private NioEventLoopGroup workerGroup;

        private ServerBootstrap serverBootstrap;


        public Start(ServerBootstrap serverBootstrap,NioEventLoopGroup bossGroup,NioEventLoopGroup workerGroup){
            this.serverBootstrap = serverBootstrap;
            this.bossGroup = bossGroup;
            this.workerGroup = workerGroup;
        }
        public void start(int port,String nettyName) throws InterruptedException {

            try {
                //构建全局缓存对象

                //绑定端口，同步等待成功
                //服务端启动辅助类配置完成之后，调用它的bind方法绑定监听端口
                //随后，调用它的同步阻塞方法sync等待绑定操作完成。
                //完成之后Netty会返回一个ChannelFuture，它的功能类似于JDK的java.util.concurrent.Future，主要用于异步操作的通知回调。
                ChannelFuture f = this.serverBootstrap.bind(port).sync();
                NettyCache cache = NettyCache.builder().channelFuture(f).name(nettyName).build();
                NETTY_CACHE_HASH_MAP.put(nettyName, cache);
                //等待服务端监听端口关闭
                //使用f.channel().closeFuture().sync()方法进行阻塞,等待服务端链路关闭之后main函数才退出。
                f.channel().closeFuture().sync();
                NettyServer.NETTY_CACHE_HASH_MAP.remove(nettyName);
                log.info(nettyName+":++++++++++++++++++++++++++++服务结束，通道关闭，释放线程资源完成++++++++++++++++++++++++++++:"+nettyName);
            }finally {
                //优雅退出，释放线程池资源
                //调用NIO线程组的shutdownGracefully进行优雅退出，它会释放跟shutdownGracefully相关联的资源。
                this.bossGroup.shutdownGracefully();
                this.workerGroup.shutdownGracefully();
            }
        }
    }

}
