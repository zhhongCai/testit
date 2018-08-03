package com.test.it.netty.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.rtsp.RtspHeaders.Names.CONNECTION;

/**
 * @Author: caizhh
 * @Date: Create in 18-6-22 下午7:05
 * @Description:
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private String url;

    public HttpFileServerHandler(boolean autoRelease, String url) {
        super(autoRelease);
        this.url = url;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        if (!req.getDecoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        if (req.getMethod() != HttpMethod.GET) {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }

        final String uri = req.getUri();
        final String path = sanitizeUri(uri);
        if (path == null) {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }

        if (file.isDirectory()) {
            sendFileList(ctx, file);
            return;
        }

        if (!file.isFile()) {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileLength = randomAccessFile.length();

            HttpResponse resp = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            setContentLength(resp, fileLength);
            setContentTypeHeader(resp, file);
            if (isKeepAlive(req)) {
                resp.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(resp);

            ctx.writeAndFlush(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise())
                    .addListener(new ChannelProgressiveFutureListener() {
                        @Override
                        public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                            System.out.println("operationProgressed: progress=" + progress + ", total=" + total);
                        }

                        @Override
                        public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                            System.out.println("operationComplete !!!");
                        }
            });
        }

    }

    private void setContentTypeHeader(HttpResponse resp, File file) {
        resp.headers().set(CONTENT_TYPE, "*/*");
    }

    private void sendFileList(ChannelHandlerContext ctx, File file) {
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        resp.headers().set(CONTENT_TYPE, "text/html;charset=TUF-8");

        StringBuilder html = new StringBuilder("<ul>");
        html.append("<li>file link: <a href=\"../\">..</a></li>");
        for (File f : file.listFiles()) {
            //TODO
            html.append("<li>file link: <a href=\"").append(getPath(f)).append("\">").append(f.getName()).append("</a></li>");
        }
        html.append("</ul>");

        ByteBuf bf = Unpooled.copiedBuffer(html, CharsetUtil.UTF_8);
        resp.content().writeBytes(bf);
        bf.release();
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }

    private String getPath(File file) {
        return file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(url));
    }

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (Exception e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        uri = uri.replace("/", File.separator);
        if (uri.contains(File.separator + ".")
                || uri.contains("." + File.separator)
                || uri.startsWith(".")
                || uri.endsWith(".") || !uri.contains(url)) {
            return null;
        }
        return System.getProperty("user.dir") + File.separator + uri;
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer("Failure: " + status.toString() + "\n\r", CharsetUtil.UTF_8));
        resp.headers().set(CONTENT_TYPE, "text/plain;charset=TUF-8");
        ctx.channel().writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
