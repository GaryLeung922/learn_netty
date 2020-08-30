package com.xiaojiaqi.netty.example.geek.server.codec;

import com.xiaojiaqi.netty.example.geek.common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 二次解码器
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:25 PM
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<ResponseMessage> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();

        responseMessage.encode(buffer);

        list.add(buffer);
    }
}
