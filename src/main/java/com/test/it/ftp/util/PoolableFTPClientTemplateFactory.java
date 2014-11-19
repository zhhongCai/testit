package com.test.it.ftp.util;

import org.apache.commons.pool.PoolableObjectFactory;
import sun.net.ftp.FtpClient;

/**
 * Author: caizh
 * CreateTime: 2014/9/28 15:08
 * Version: 1.0
 */
public class PoolableFTPClientTemplateFactory implements PoolableObjectFactory<FtpClientTemplate> {


    @Override
    public FtpClientTemplate makeObject() throws Exception {
        FtpClient ftpClient = new FtpClient();
        return new FtpClientTemplate(ftpClient);
    }

    @Override
    public void destroyObject(FtpClientTemplate obj) throws Exception {

    }

    @Override
    public boolean validateObject(FtpClientTemplate obj) {
        return false;
    }

    @Override
    public void activateObject(FtpClientTemplate obj) throws Exception {

    }

    @Override
    public void passivateObject(FtpClientTemplate obj) throws Exception {

    }
}
