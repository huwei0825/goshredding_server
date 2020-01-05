package com.tony.goshredding.service;

import com.tony.goshredding.util.GoHelper;
import com.tony.goshredding.vo.ConfigVO;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * This is a rmi client and store the current login user information.
 *
 * @author Songyun hu.
 */
public class GoService {

    public static String currentUserName = "";//store the current login user name.
    public static String currentUserId = "";//store the current login user id.
    public static int currentUserType = 0;//store the current login user type.
    private static IGoService _GoService = null;//store the rmi server.

    public static IGoService getInstance() {
        try {
            if (_GoService == null) {
                //read the rmi ip and port from the config.ini file.
                ConfigVO configVO = GoHelper.readConfig();
                _GoService = (IGoService) Naming.lookup("rmi://" + configVO.ipAddress + ":" + configVO.ipPort + "/GoService");

                System.out.println(">>>>>INFO:GoClient connect " + configVO.ipAddress + ":" + configVO.ipPort + " service successful！");
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _GoService;
    }

}
