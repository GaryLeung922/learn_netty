package com.xiaojiaqi.netty.c5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/11 8:11 AM
 */
public class Main {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 14);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte)'J');
        System.out.println(sliced.toString(utf8));
        assert buf.getByte(0) == sliced.getByte(0);
    }
}
