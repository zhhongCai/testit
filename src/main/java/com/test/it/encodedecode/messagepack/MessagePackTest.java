package com.test.it.encodedecode.messagepack;

import com.google.common.collect.Lists;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.List;

/**
 * @Author: caizhh
 * @Date: Create in 18-6-20 上午8:43
 * @Description:
 */
public class MessagePackTest {

    public static void main(String[] args) throws IOException {
        List<String> list = Lists.newArrayList("1", "2", "3", "4");
        MessagePack pack = new MessagePack();
        byte[] d = pack.write(list);

        List<String> list2 = pack.read(d, Templates.tList(Templates.TString));

        System.out.println(list2);
    }
}
