package com.xiaojiaqi.netty.example.geek.client;

import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameEncoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolEncoder;
import com.xiaojiaqi.netty.example.geek.common.RequestMessage;
import com.xiaojiaqi.netty.example.geek.common.order.OrderOperation;
import com.xiaojiaqi.netty.example.geek.util.IdUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 10:17 PM
 */
public class ClientV0 {
    public static void main(String[] args) throws Exception {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup())
                .handler(new ChannelInitializer<NioSocketChannel>() {
                             @Override
                             protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                 ChannelPipeline pipeline = nioSocketChannel.pipeline();
                                 pipeline.addLast(new OrderFrameDecoder());
                                 pipeline.addLast(new OrderFrameEncoder());
                                 pipeline.addLast(new OrderProtocolDecoder());
                                 pipeline.addLast(new OrderProtocolEncoder());
                                 pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                             }
                         });

        ChannelFuture future = bootstrap.connect("127.0.0.1",8090).sync();

        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), new OrderOperation(1001, "tudou"));

        for(int i=0;i<10;i++){
            future.channel().writeAndFlush(requestMessage);
        }

        future.channel().closeFuture().get();

    }
}
