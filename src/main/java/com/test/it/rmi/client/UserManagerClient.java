package com.test.it.rmi.client;

import com.test.it.rmi.server.Account;
import com.test.it.rmi.server.IUserManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by caizh on 2015/8/24 0024.
 */
public class UserManagerClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8808);
            IUserManager userManager = (IUserManager) registry.lookup("userManager");
            Account account = userManager.sayHi("test", 22);
            System.out.println("name="+account.getName() + ",age=" + account.getAge());
            userManager.printSth(account);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
