package com.xiaojiaqi.netty.example.geek.client.handler;

import com.xiaojiaqi.netty.example.geek.client.codec.dispatcher.OperationResultFuture;
import com.xiaojiaqi.netty.example.geek.client.codec.dispatcher.RequestPendingCenter;
import com.xiaojiaqi.netty.example.geek.common.RequestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/19 8:40 AM
 */
public class RequestDispatcherHandler extends ChannelOutboundHandlerAdapter {

    private RequestPendingCenter requestPendingCenter;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("RequestDispatcherHandler");
        if(msg instanceof RequestMessage){
            System.out.println("msg instanceof RequestMessage");
            OperationResultFuture operationResultFuture = new OperationResultFuture();
            requestPendingCenter.add(((RequestMessage)msg).getMessageHeader().getStreamId(), operationResultFuture);
            System.out.println(requestPendingCenter.map.keySet());
        }else {
            System.out.println("msg not instanceof RequestMessage");
        }
        super.write(ctx, msg, promise);
    }

    public RequestDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }
}

