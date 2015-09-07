package com.test.it.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 1. 创建接口类A并继承Remote
 * 2. 创建接口A的实现类
 * 3. 注册绑定
 * Created by caizh on 2015/8/24 0024.
 */
public class UserManagerServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        UserManagerImpl userManager = new UserManagerImpl();
        IUserManager userManagerI  = (IUserManager)UnicastRemoteObject.exportObject(userManager, 8808);
        Registry registry = LocateRegistry.createRegistry(8808);
        registry.rebind("userManager", userManager);
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager (new RMISecurityManager() );
//        }
//        Naming.bind("userManager", userManager);
        System.out.println("server is ready");

    }
}
