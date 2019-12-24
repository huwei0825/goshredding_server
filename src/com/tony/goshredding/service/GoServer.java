package com.tony.goshredding.service;
import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.vo.ConfigVO;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
/**
 * This is a rmi server.
 * @author Songyun hu
 */
public class GoServer {
    public static void main(String args[]) {
        try {
            //read the rmi ip and port from the config.ini file.
            ConfigVO configVO =GoHelper.readConfig();
            
            IGoService rGoService = new GoServiceImpl();
            LocateRegistry.createRegistry(Integer.parseInt(configVO.ipPort));
            
            System.setProperty("java.rmi.server.hostname",configVO.ipAddress);
            Naming.bind("rmi://"+configVO.ipAddress+":"+configVO.ipPort+"/GoService", rGoService);
            System.out.println(">>>>>INFO:GoServer bind "+configVO.ipAddress+":"+configVO.ipPort+" successful！");
            
        } catch (RemoteException e) {
            System.out.println("Create remote object failed！");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("Already bind Exception！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception！");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
