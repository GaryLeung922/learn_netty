package com.xiaojiaqi.netty.example.geek.client.codec;

import com.xiaojiaqi.netty.example.geek.common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 二次解码器
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:25 PM
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("OrderProtocolDecoder");
        ResponseMessage requestMessage = new ResponseMessage();
        requestMessage.decode(byteBuf);

        list.add(requestMessage);
    }
}
