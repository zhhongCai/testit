package com.test.it.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author: caizhh
 * @Date: Create in 18-8-2 上午11:52
 * @Description:
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m);
        m.release();

        if (buf.readableBytes() >= 4) {
            long time = (buf.readUnsignedInt() - UnixTime.TIME_IN_SECONDS) * 1000;
            System.out.println(new Date(time));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
