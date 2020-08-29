package com.xiaojiaqi.netty.c2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;

import java.util.Date;

import static com.xiaojiaqi.netty.c2.Constant.NETTY_CHANNEL_KEY;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/6 8:35 PM
 */
public class EchoClientAttributeHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Attribute<NettyChannel> attr = ctx.attr(NETTY_CHANNEL_KEY);
        NettyChannel nChannel = attr.get();
        if (nChannel == null) {
            NettyChannel newNChannel = new NettyChannel("HelloWorld0Client", new Date());
            nChannel = attr.setIfAbsent(newNChannel);
        } else {
            System.out.println("channelActive attributeMap 中是有值的");
            System.out.println(nChannel.getName() + "=======" + nChannel.getCreateDate());
        }
        System.out.println("HelloWorldC0ientHandler Active");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Attribute<NettyChannel> attr = ctx.attr(NETTY_CHANNEL_KEY);
        NettyChannel nChannel = attr.get();
        if (nChannel == null) {
            NettyChannel newNChannel = new NettyChannel("HelloWorld0Client", new Date());
            nChannel = attr.setIfAbsent(newNChannel);
        } else {
            System.out.println("channelRead attributeMap 中是有值的");
            System.out.println(nChannel.getName() + "=======" + nChannel.getCreateDate());
        }
        System.out.println("HelloWorldClientHandler read Message:" + msg);

        ctx.fireChannelRead(msg);

    }
}
