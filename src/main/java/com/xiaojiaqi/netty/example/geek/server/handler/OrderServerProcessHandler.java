package com.xiaojiaqi.netty.example.geek.server.handler;

import com.xiaojiaqi.netty.example.geek.common.Operation;
import com.xiaojiaqi.netty.example.geek.common.OperationResult;
import com.xiaojiaqi.netty.example.geek.common.RequestMessage;
import com.xiaojiaqi.netty.example.geek.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:29 PM
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {

        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        channelHandlerContext.writeAndFlush(responseMessage);
    }
}
