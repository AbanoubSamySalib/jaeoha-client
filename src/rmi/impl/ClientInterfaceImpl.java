/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.impl;

import ConnectToServer.Connect;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import model.ClientModel;
import databaseclasses.Message;
import databaseclasses.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import view.homescreen.HomeController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import rmi.interfaces.ClientInterface;
import static view.login.LoginInController.stage;

/**
 *
 * @author abanoub samy
 */
public class ClientInterfaceImpl extends UnicastRemoteObject implements ClientInterface {

    ClientModel model = new ClientModel();
    // HomeController homecontroller;

    /**
     *
     * @throws RemoteException
     */
    public ClientInterfaceImpl() throws RemoteException {

    }

    /**
     *
     * @param u
     * show desktop notification 
     * @throws RemoteException
     * 
     * 
     */
    @Override
    public void informOnlineFriends(Users u) throws RemoteException {

        model.showOnlineNotification(u);

    }

    /**
     *
     * @param message
     * @throws RemoteException
     */
    @Override
    public void RecieveMessage(Message message) throws RemoteException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.err.println(message.getMessageText());

        System.out.println("reciveeeeeeeeeeeed" + message.getMessageText());
        model.recieveMessage(message);

    }

    /**
     *
     * @throws RemoteException
     */
    @Override
    public void updateUrAdminNOtification() throws RemoteException {

        model.updateAdminNotif();

    }

    /**
     *
     * @throws RemoteException
     */
    @Override
    public void updateUrRequestNOtification() throws RemoteException {

        model.updateRequestNotif();

    }

    /**
     *
     * @throws RemoteException
     */
    @Override
    public void updateUrResponseNOtification() throws RemoteException {

        model.updateResponseNotif();

    }

    /**
     *When the server turn on it call this function to make the user application connect to the server.
     * @throws RemoteException
     * 
     */
    @Override
    public void RecieveServerRemoteObject() throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    model = HomeController.model;
                    Connect connect = new Connect();
                    model.setServerRemoteObject(Connect.getServerRemoteObject());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homescreen/Home.fxml"));
                    HomeController controller = new HomeController();
                    loader.setController(controller);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controller.setStage(stage);
                    controller.setC(HomeController.model);
                    controller.setU(HomeController.u);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                } catch (NotBoundException ex) {
                    Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    /**
     *When the server turn off it calls this function to stop the user application scene.
     * @throws RemoteException
     * 
     */
    @Override
    public void stopScene() throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/serverDown/serverDown.fxml"));
                    Group root = new Group();
                    Node node = loader.load();
                    root.getChildren().add(node);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    //saveeeee

    /**
     *
     * @param ris
     * @param fileName
     * @throws RemoteException
     */
    @Override
    public void recieve(RemoteInputStream ris, String fileName) throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {

                    InputStream fileData = RemoteInputStreamClient.wrap(ris);
                    
                    

                    System.out.println("file recieved successfully");

                    OutputStream outputStream
                            = new FileOutputStream(new File(fileName));

                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = fileData.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                    System.out.println("done writing");

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }
        });

    }

    /**
     *To update friend list when there is new friend.
     * @throws RemoteException
     * 
     */
    @Override
    public void updateList() throws RemoteException {

        try {
            model.updateFriendList();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param sender
     * show file request dialogue to specified  user
     * @throws RemoteException
     * 
     */
    @Override
    public void fileRequest(Users sender) throws RemoteException {

        model.showDialogue(sender);
    

    }

    /**
     * show refused dialogue to specified user
     * @throws RemoteException
     *
     */
    @Override
    public void refused() throws RemoteException {



        model.showRefuseDial();

    }

    /**
     *accept file request and start to send file to specified user
     * @throws RemoteException
     * 
     * 
     */
    @Override
    public void accept() throws RemoteException {

        try {
            model.sendFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
