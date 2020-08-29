package com.xiaojiaqi.netty.c5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/11 8:17 AM
 */
public class ByteBufExample {

    public static void main(String[] args) {
        //write();
        release();
    }

    public static void get(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte)'B');
        System.out.println((char)buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }

    public static void write(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.readByte());
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        System.out.println(readerIndex+":"+writerIndex);
        buf.writeByte((byte)'?');
        System.out.println(buf.toString(utf8));
        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();
    }

    public static void release(){
        PooledByteBufAllocator alloc = new PooledByteBufAllocator();
        ByteBuf byteBuf = alloc.directBuffer();
        System.out.println(byteBuf.refCnt());
        byteBuf.release();
        System.out.println(byteBuf.refCnt());
    }
}
