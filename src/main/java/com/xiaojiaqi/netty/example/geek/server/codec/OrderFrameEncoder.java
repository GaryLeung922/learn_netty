package com.xiaojiaqi.netty.example.geek.server.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:23 PM
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super(2);
    }
}
