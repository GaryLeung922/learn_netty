package com.xiaojiaqi.netty.example.geek.server;

import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameEncoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolEncoder;
import com.xiaojiaqi.netty.example.geek.server.handler.MetricHandler;
import com.xiaojiaqi.netty.example.geek.server.handler.OrderServerProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:38 PM
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        UnorderedThreadPoolEventExecutor unorderedThreadPoolEventExecutor = new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("business"));
        NioEventLoopGroup boss = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
        NioEventLoopGroup worker =  new NioEventLoopGroup(8, new DefaultThreadFactory("worker"));
        MetricHandler metricHandler = new MetricHandler();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    }
                })
                .group(boss, worker)

                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast("OrderFrameDecoder", new OrderFrameDecoder());
                        pipeline.addLast("OrderFrameEncoder" ,new OrderFrameEncoder());

                        pipeline.addLast("metricHannel", metricHandler);
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast(new OrderProtocolDecoder());
                        pipeline.addLast(new OrderProtocolEncoder());
                        pipeline.addLast(unorderedThreadPoolEventExecutor,new OrderServerProcessHandler());
                    }
                });

        ChannelFuture future = serverBootstrap.bind(8090).sync();

        future.channel().closeFuture().get();

    }
}
