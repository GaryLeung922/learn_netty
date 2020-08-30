package com.xiaojiaqi.netty.example.geek.client.codec;

import com.xiaojiaqi.netty.example.geek.common.RequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 二次解码器
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:25 PM
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<RequestMessage> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestMessage responseMessage, List<Object> list) throws Exception {
        System.out.println("OrderProtocolEncoder");
        ByteBuf buffer = channelHandlerContext.alloc().buffer();

        responseMessage.encode(buffer);

        list.add(buffer);
    }
}
