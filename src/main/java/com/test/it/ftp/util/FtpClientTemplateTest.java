package com.test.it.ftp.util;

/**
 * Author: caizh
 * CreateTime: 2014/9/28 15:33
 * Version: 1.0
 */
public class FtpClientTemplateTest {

    public FtpClientTemplatePlool ftpClientTemplatePlool = new FtpClientTemplatePlool(new PoolableFTPClientTemplateFactory());

    public static void main(String[] args) throws Exception {
        FtpClientTemplateTest ftpClientTemplateTest = new FtpClientTemplateTest();
        FtpClientTemplate ftpClientTemplate = ftpClientTemplateTest.ftpClientTemplatePlool.borrowObject();
    }
}
