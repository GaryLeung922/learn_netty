package com.xiaojiaqi.netty.example.geek.client.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/18 1:23 PM
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }



}
