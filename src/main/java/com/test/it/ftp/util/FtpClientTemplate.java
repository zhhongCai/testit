package com.test.it.ftp.util;

import sun.net.ftp.FtpClient;

/**
 * Author: caizh
 * CreateTime: 2014/9/28 15:01
 * Version: 1.0
 */
public class FtpClientTemplate {

    public FtpClient ftpClient;

    public FtpClientTemplate(FtpClient ftpClient) {
        this.ftpClient = ftpClient;
    }

}
