package com.xiaojiaqi.netty.example.geek.server.handler;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/20 1:08 PM
 */
@ChannelHandler.Sharable
public class MetricHandler extends ChannelDuplexHandler {

    private AtomicLong totalChannels = new AtomicLong();

    {
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("totalChannels", new Gauge<Long>(){

            @Override
            public Long getValue() {
                return totalChannels.get();
            }
        });

        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(5, TimeUnit.SECONDS);

        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        totalChannels.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        totalChannels.decrementAndGet();
        super.channelInactive(ctx);
    }
}
