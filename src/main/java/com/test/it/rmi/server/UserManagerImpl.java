package com.test.it.rmi.server;

import java.rmi.RemoteException;

/**
 * Created by caizh on 2015/8/24 0024.
 */
public class UserManagerImpl implements IUserManager {

    @Override
    public Account sayHi(String name, int age) throws RemoteException {
        Account account = new Account();
        account.setAge(age);
        account.setName(name);
        return account;
    }

    @Override
    public void printSth(Account account) throws RemoteException {
        System.out.println("This is " + account.getName() + ", and he is " + account.getAge());
    }
}
