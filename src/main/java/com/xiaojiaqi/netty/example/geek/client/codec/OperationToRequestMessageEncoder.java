package com.xiaojiaqi.netty.example.geek.client.codec;

import com.xiaojiaqi.netty.example.geek.common.Operation;
import com.xiaojiaqi.netty.example.geek.common.RequestMessage;
import com.xiaojiaqi.netty.example.geek.util.IdUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 10:30 PM
 */
public class OperationToRequestMessageEncoder extends MessageToMessageEncoder<Operation> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> list) throws Exception {

        System.out.println("OperationToRequestMessageEncoder");
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);
        list.add(requestMessage);

    }
}
