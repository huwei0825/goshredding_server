/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.service;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author SXR
 */
public class GoServer {
    public static void main(String args[]) {
        try {
            IGoService rGoService = new GoServiceImpl();
            LocateRegistry.createRegistry(8888);

            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            Naming.bind("rmi://localhost:8888/GoService", rGoService);
            System.out.println(">>>>>INFO:GoService bind successful！");
        } catch (RemoteException e) {
            System.out.println("Create remote object failed！");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("Already bind Exception！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception！");
            e.printStackTrace();
        }
    }
}
