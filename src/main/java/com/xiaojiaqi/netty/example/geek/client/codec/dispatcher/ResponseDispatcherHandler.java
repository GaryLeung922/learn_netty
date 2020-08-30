package com.xiaojiaqi.netty.example.geek.client.codec.dispatcher;

import com.xiaojiaqi.netty.example.geek.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/19 8:17 AM
 */
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private RequestPendingCenter requestPendingCenter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        System.out.println("ResponseDispatcherHandler");
        requestPendingCenter.set(msg.getMessageHeader().getStreamId(), msg.getMessageBody());
    }

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }
}
