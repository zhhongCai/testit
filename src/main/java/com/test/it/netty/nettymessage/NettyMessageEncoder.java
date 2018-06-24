package com.test.it.netty.nettymessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.jboss.marshalling.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by caizh on 18-6-23.
 */
public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private Marshaller marshaller;

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    public NettyMessageEncoder() throws IOException {
        this.marshaller = buildMarshaller();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (msg ==  null || msg.getHeader() == null) {
            throw new Exception("The encode message is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionId());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            value = param.getValue();

            encode(value, sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;

        if (msg.getBody() != null) {
            encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
            sendBuf.setInt(4, sendBuf.readableBytes());
        }
    }
    /**
     * 创建Jboss Marshalling解码器MarshallingDecoder
     * @return MarshallingDecoder
     */
    public static Unmarshaller buildUnmarshaller() throws IOException {
        //首先通过Marshalling工具类的精通方法获取Marshalling实例对象 参数serial标识创建的是java序列化工厂对象。
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        //创建了MarshallingConfiguration对象，配置了版本号为5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createUnmarshaller(configuration);
    }

    /**
     * 创建Jboss Marshalling编码器MarshallingEncoder
     * @return MarshallingEncoder
     */
    public static Marshaller buildMarshaller() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createMarshaller(configuration);
    }

    protected void encode(Object msg, ByteBuf out) {
        try {

            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            ByteOutput output = new ChannelBufferByteOutput (out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                marshaller.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
