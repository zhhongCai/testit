package com.test.it.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by caizh on 2015/8/24 0024.
 */
public interface IUserManager extends Remote {
    public Account sayHi(String name, int age) throws RemoteException;

    public void printSth(Account account) throws RemoteException;
    ;
}
