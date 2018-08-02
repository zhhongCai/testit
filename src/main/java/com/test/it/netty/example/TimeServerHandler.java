package com.test.it.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: caizhh
 * @Date: Create in 18-8-1 下午3:31
 * @Description:
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf time = ctx.alloc().buffer(4);

        /*
         * https://tools.ietf.org/html/rfc868
         * 2208988800是1970-01-01 00:00.00距离1900-01-01  00:00.00的秒数
         */
        long t = 2208988800L;

        time.writeInt((int) (System.currentTimeMillis() / 1000L + t));

        ChannelFuture f = ctx.writeAndFlush(time);

        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
