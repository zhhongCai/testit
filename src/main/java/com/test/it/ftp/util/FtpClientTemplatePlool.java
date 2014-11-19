package com.test.it.ftp.util;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Author: caizh
 * CreateTime: 2014/9/28 14:54
 * Version: 1.0
 */
public class FtpClientTemplatePlool extends GenericObjectPool<FtpClientTemplate> {
    public FtpClientTemplatePlool(PoolableObjectFactory<FtpClientTemplate> factory) {
        super(factory);
    }
}
