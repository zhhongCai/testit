package com.test.it.fastdfs;

import com.test.it.fastdfs.util.FastdfsUtil;
import org.junit.Test;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-08-21 08:51
 * @Description:
 */
public class FastdfsTest {

    @Test
    public void testUpload() throws Exception {
        FastdfsUtil fastdfsUtil = new FastdfsUtil("classpath:fastdfs-client.properties");

        String ret = fastdfsUtil.uploadFile("test.txt");
        System.out.println(ret);
    }
}
