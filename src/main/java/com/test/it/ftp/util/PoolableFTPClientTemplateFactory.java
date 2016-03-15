package com.test.it.ftp.util;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * Author: caizh
 * CreateTime: 2014/9/28 15:08
 * Version: 1.0
 */
public class PoolableFTPClientTemplateFactory implements PoolableObjectFactory<FtpClientTemplate> {


    public FtpClientTemplate makeObject() throws Exception {
//        FtpClient ftpClient = new FtpClient();
//        return new FtpClientTemplate(ftpClient);
        return null;
    }

    public void destroyObject(FtpClientTemplate obj) throws Exception {

    }

    public boolean validateObject(FtpClientTemplate obj) {
        return false;
    }

    public void activateObject(FtpClientTemplate obj) throws Exception {

    }

    public void passivateObject(FtpClientTemplate obj) throws Exception {

    }
}
