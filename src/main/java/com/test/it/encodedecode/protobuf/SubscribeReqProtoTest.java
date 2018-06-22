package com.test.it.encodedecode.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.test.it.netty.protobuf.pojo.SubscribeReqProto;

/**
 * @Author: caizhh
 * @Date: Create in 18-6-21 上午8:58
 * @Description:
 */
public class SubscribeReqProtoTest {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] req) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(req);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1).setUserName("test").setProductName("product").setAddress("xm");
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("before encode:\n " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("after decode:\n " + req2.toString());

        System.out.println("req equals req =" + req.equals(req2));
    }
}
