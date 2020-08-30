package com.xiaojiaqi.netty.example.geek.client;

import com.xiaojiaqi.netty.example.geek.client.codec.OperationToRequestMessageEncoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderFrameEncoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolDecoder;
import com.xiaojiaqi.netty.example.geek.client.codec.OrderProtocolEncoder;
import com.xiaojiaqi.netty.example.geek.client.codec.dispatcher.OperationResultFuture;
import com.xiaojiaqi.netty.example.geek.client.codec.dispatcher.RequestPendingCenter;
import com.xiaojiaqi.netty.example.geek.client.codec.dispatcher.ResponseDispatcherHandler;
import com.xiaojiaqi.netty.example.geek.client.handler.RequestDispatcherHandler;
import com.xiaojiaqi.netty.example.geek.common.OperationResult;
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
public class ClientV2 {
    public static void main(String[] args) throws Exception {
        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
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
                                 pipeline.addLast(new ResponseDispatcherHandler(requestPendingCenter));
                                 pipeline.addLast(new RequestDispatcherHandler(requestPendingCenter));
                                 pipeline.addLast(new OperationToRequestMessageEncoder());
                                 pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                             }
                         });

        ChannelFuture future = bootstrap.connect("127.0.0.1",8090).sync();

        System.out.println(future.isDone()+""+future.isSuccess());
        long streamId = IdUtil.nextId();

        System.out.println("streamId: "+ streamId);

        RequestMessage requestMessage = new RequestMessage(streamId, new OrderOperation(1021, "tudou"));

        ChannelFuture await = future.channel().writeAndFlush(requestMessage).await();

        System.out.println(await.isDone()+":"+await.isSuccess());

        OperationResultFuture operationResultFuture = requestPendingCenter.get(streamId);
        OperationResult operationResult = null;
        try{
            operationResult = operationResultFuture.get();
        }catch (Exception e){
            System.out.println(requestPendingCenter.map.keySet());
        }
        
        System.out.println(operationResult);

        future.channel().closeFuture().get();

    }
}
