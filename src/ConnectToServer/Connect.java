/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectToServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.interfaces.ServerInterface;

/**
 *
 * @author abanoub samy
 */
public class Connect {
    
    
    
  private   Registry registry;
  private  static ServerInterface serverRemoteObject;

    /**
     *get remote object of server form the registry
     * @throws NotBoundException
     * @throws RemoteException
     * 
     */
    public Connect() throws NotBoundException, RemoteException {
        
     
          registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
          serverRemoteObject = (ServerInterface) registry.lookup("chat");
          
          System.out.println("client connected successfully");
     
    }

    /**
     * @return the registry
     */
    public Registry getRegistry() {
        return registry;
    }

    /**
     * @param registry the registry to set
     */
    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    /**
     * @return the serverRemoteObject
     */
    public static ServerInterface getServerRemoteObject() {
        return serverRemoteObject;
    }

    /**
     * @param aServerRemoteObject the serverRemoteObject to set
     */
    public static void setServerRemoteObject(ServerInterface aServerRemoteObject) {
        serverRemoteObject = aServerRemoteObject;
    }

  
   
}
