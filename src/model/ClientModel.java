/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import ConnectToServer.Connect;
import Save.ListMessages;
import Save.SaveXml;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
import view.login.LoginInController;
import databaseclasses.Chat;
import databaseclasses.Message;
import databaseclasses.Users;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import view.homescreen.HomeController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.impl.ClientInterfaceImpl;
import rmi.interfaces.ClientInterface;
import rmi.interfaces.ServerInterface;
import view.signup.SignUpController;

/**
 *
 * @author abanoub samy
 */
public class ClientModel {

    File f;
    Chat c;

    private ServerInterface serverRemoteObject;

    /**
     *
     * @return
     */
    public ServerInterface getServerRemoteObject() {
        return serverRemoteObject;
    }

    /**
     *
     * @param serverRemoteObject
     */
    public void setServerRemoteObject(ServerInterface serverRemoteObject) {
        this.serverRemoteObject = serverRemoteObject;
    }

    /**
     *
     */
    public ClientModel() {

        serverRemoteObject = Connect.getServerRemoteObject();

    }

    //sign up

    /**
     *
     * @param data
     * @return
     * @throws RemoteException
     */
    public Users signUp(String[] data) throws RemoteException {

        Users u = new Users();
        u.setUserName(data[0]);
        u.setEmail(data[1]);
        u.setPassword(data[2]);
        u.setGender(data[3]);
        u = serverRemoteObject.signUp(u);

        if (u == null) {
            System.out.println("change email email is already exist");
            return null;

        } else {
            ClientInterface clientRemoteObject = new ClientInterfaceImpl();
            if (serverRemoteObject.register(clientRemoteObject, u) == true) {
                return u;
            } else {
                //write message m3ml4 register 
                return null;
            }

        }

    }

    //sign in

    /**
     *
     * @param data
     * @return
     * @throws RemoteException
     */
    public Users signIn(String[] data) throws RemoteException {
        Users u = new Users();

        u.setEmail(data[0]);
        u.setPassword(data[1]);
        u = serverRemoteObject.signIn(u);
        if (u == null) {
            System.out.println("user ont exist u should sign up first");

            return null;

        } else {
            System.err.println("useriD" + u.getId());
            System.err.println("userimage" + u.getImage());
            ClientInterface clientRemoteObject = new ClientInterfaceImpl();
            if (serverRemoteObject.register(clientRemoteObject, u) == true) {
                return u;
            } else {
                //atb3 error something wrong happend;
                return null;
            }

        }

    }

    //check sign out

    /**
     *
     * @param u
     * @return
     * @throws RemoteException
     */
    public boolean signOut(Users u) throws RemoteException {

        if (serverRemoteObject.signOut(u)) {
            System.out.println("signed out successfully");
            return true;
        } else {
            System.out.println("error while signing out ");
            return false;
        }
    }

    //change my status

    /**
     *
     * @param u
     * @param status
     * @throws RemoteException
     */
    public void changeStatus(Users u, String status) throws RemoteException {
        System.out.println("current status" + u.getStatus());
        u = serverRemoteObject.changeMyStatus(u, status);

        System.out.println("new status" + u.getStatus());

    }

    //get friend requests 

    /**
     *
     * @param u
     * @return
     * @throws RemoteException
     */
    public Users getfriendRequests(Users u) throws RemoteException {

        u.setFriendRequests(serverRemoteObject.myFriendRequests(u));

        return u;

    }

    // get friends

    /**
     *
     * @param u
     * @return
     * @throws RemoteException
     */
    public Users getfriends(Users u) throws RemoteException {

        u.setFriends(serverRemoteObject.myFriends(u));

        return u;

    }

    // get admin notifications

    /**
     *
     * @param u
     * @return
     * @throws RemoteException
     */
    public Users getNotifications(Users u) throws RemoteException {

        u.setAdminNotification(serverRemoteObject.myAdminNotifications(u));

        return u;

    }

    // get responses 

    /**
     *
     * @param u
     * @return
     * @throws RemoteException
     */
    public Users getResponses(Users u) throws RemoteException {

        u.setNotification(serverRemoteObject.myNotifications(u));

        return u;
    }

    /**
     *
     * @param user
     * @return
     * @throws RemoteException
     */
    public Users updatemyData(Users user) throws RemoteException {

        return serverRemoteObject.updateUserData(user);
    }

    //search for  new user

    /**
     *
     * @param me
     * @param email
     * search for user to add to friend list
     * @return
     * @throws RemoteException
     */
    public Users searchForUser(Users me, String email) throws RemoteException {

        Users u = serverRemoteObject.selectByEmail(email);

        if (u != null) {
            System.out.println(u.getUserName());
            System.out.println(u.getActive());

            return u;

        } else {

            System.out.println("no users found");
            return null;
        }

    }

    //check if user requested or not 

    /**
     *
     * @param me
     * @param u
     * 
     * check if friend request is sent to specified user or not
     * @return
     * @throws RemoteException
     */
    public boolean checkRequestedOrNot(Users me, Users u) throws RemoteException {
        boolean f = serverRemoteObject.checkRequestedOrNot(me, u);
        if (f) {

            System.out.println("requested");
            return true;
        } else {
            System.out.println("not requested add him");

            return false;

        }

    }

    //add friend 

    /**
     *
     * @param me
     * @param u
     * add friend to friend list
     * @return
     * @throws RemoteException
     */
    public boolean addUser(Users me, Users u) throws RemoteException {
        boolean add = serverRemoteObject.sendFriendRequest(me, u);

        if (add) {

            System.out.println("request sent");
            return true;
        }

        return false;

    }

    //accept request

    /**
     *
     * @param me
     * @param u
     * accept friend request
     * @throws RemoteException
     */
    public void acceptRequest(Users me, Users u) throws RemoteException {

        boolean accept = serverRemoteObject.acceptRequest(me, u);
        deleteRequest(me, u);

    }

    //delete request

    /**
     *
     * @param me
     * @param u
     * delete friend request
     * @throws RemoteException
     */
    public void deleteRequest(Users me, Users u) throws RemoteException {
        boolean delete = serverRemoteObject.deleteRequest(me, u);
    }

    // show Online notification

    /**
     *
     * @param u
     * show desktop online notification
     */
    public void showOnlineNotification(Users u) {

//        System.out.println(u.getUserName() + "  is online");
//
//        homeController.showPopUpNotification(u);
        if (SignUpController.getController() == null) {
            LoginInController.getController().showPopUpNotification(u);

        } else {

            SignUpController.getController().showPopUpNotification(u);
        }
    }

    //check lma y3ml search 3shan y3ml add

    /**
     *
     * @param me
     * @param u
     * check if specified user is in friend list or not
     * @return
     */
    public boolean isFriend(Users me, Users u) {
        for (int i = 0; i < me.getFriends().size(); i++) {
            if (Objects.equals(me.getFriends().get(i).getId(), u.getId())) {
                return true;
            }
        }

        return false;

    }

    /**
     * update notification list when new notification is added in database
     */
    public void updateAdminNotif() {

        if (SignUpController.getController() == null) {

            LoginInController.getController().newAdminNotification();
        } else {

            SignUpController.getController().newAdminNotification();

        }

    }

    /**
     *update request notification list when new notification is added in database
     */
    public void updateRequestNotif() {

        if (SignUpController.getController() == null) {

            LoginInController.getController().newRequestNotif();

        } else {
            SignUpController.getController().newRequestNotif();

        }

    }

    /**
     *update response notification list when new notification is added in database
     */
    public void updateResponseNotif() {

        if (SignUpController.getController() == null) {
            LoginInController.getController().newResponseNotif();

        } else {
            SignUpController.getController().newResponseNotif();

        }

    }

    //esraaaaaaaaaaaaaaaaaaa functions ------------------------
    //esraaaaaaa

    /**
     *
      * @param user object of class user represent the user for which the method select all of his chats
     * @param friend 
     * @return chat
     * @throws RemoteException
     */
    public Chat get_individualChat(Users user, Users friend) throws RemoteException//chat between user and his friend
    {
        return serverRemoteObject.get_individualChat(user, friend);
    }

    /**
     *
    * @param c chat object carried the id of chat to be selected.
     * @return arraylist of selected messages.
     * @throws RemoteException
     */
    public ArrayList<Message> getChatMessage(Chat c) throws RemoteException {
        return serverRemoteObject.getChatMessage(c);
    }

// save chat messages

    /**
     *
     * @param Current_chat
     * 
     * save messages to xml file
     * @throws RemoteException
     * @throws FileNotFoundException
     * 
     * 
     */
    public void saveMessages(Chat Current_chat) throws RemoteException, FileNotFoundException {

        ArrayList<Message> m = getChatMessage(Current_chat);

        ListMessages allMessages = new ListMessages();

        for (int i = 0; i < m.size(); i++) {

            if (!Objects.equals(m.get(i).getSenderId(), HomeController.u.getId())) {

                m.get(i).setPos(0);
            } else {

                m.get(i).setPos(1);
            }
            allMessages.add(m.get(i));
        }

        //abanoubbbbbbbbbbbb 19/1 
        SaveXml s = new SaveXml();
        s.save(allMessages, Current_chat.getChatName());

    }

    /**
     *
      * @param m object of message to be sent.
     * @throws RemoteException
     */
    public void sendMessage(Message m) throws RemoteException {
        serverRemoteObject.sendMessage(m);
    }

    /**
     *
     * @param user
     * @return
     */
    public ArrayList<Users> getMyFriends(Users user) {
        ArrayList<Users> friends = null;
        try {
            friends = serverRemoteObject.myFriends(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friends;
    }

//esraaaaas

    /**
     *
     * @param messages
     * 
     * show message list on the chat box.
     * @throws IOException
     * 
     */
    public void showMessages(ArrayList<Message> messages) throws IOException {//hnaa t3deelesraaaaaaaaaaaaaaaaaaaa

        if (SignUpController.getController() == null) {

            LoginInController.getController().showMessages(messages);

        } else {
            SignUpController.getController().showMessages(messages);

        }
    }

    /**
     *
     * @param message
     * recieve message from server
     */
    public void recieveMessage(Message message)//hnaa t3deelesraaaaaaaaaaaaaaaaaaaa
    {
        System.out.println("fy el modelllllll" + message.getMessageText());
        LoginInController.getController().addnewMessage_show(message);

    }

    /**
     *
     * @param user object of class user represent the user for which the method select all of his recent chats
     * @return arraylist of chats
     */
    public ArrayList<Chat> get_allRecentChats(Users user) {
        ArrayList<Chat> c = null;
        try {
            c = serverRemoteObject.get_allRecentChats(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    /**
     *
     * @param user object of class user represent the user for which the method select all of his individual Chats
     * @return arraylist of chats
     */
    public ArrayList<Chat> get_allIndividualChats(Users user) {
        ArrayList<Chat> c = null;
        try {
            c = serverRemoteObject.get_allIndividualChats(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //esraaa

    /**
     *
     * @param chat
     * retrieve all Users who exists in particular chat 
     * 
     * @return ArrayList Users
     */
    public ArrayList<Users> getChatUsers(Chat chat) {
        ArrayList<Users> users = null;
        try {
            users = serverRemoteObject.getChatUsers(chat);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    //esraaa

    /**
     *
      * @param user object of class user represent the user for which the method select all of his group Chats
     * @return arraylist of chats
     */
    public ArrayList<Chat> get_allGroupChats(Users user) {
        ArrayList<Chat> chats = null;
        try {
            chats = serverRemoteObject.get_allGroupChats(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chats;
    }
    //esraaa

    /**
     *
     * @param c chat object contains chat information 
     * @param members members of chat group
     * @return created chat 
     */
    public Chat createChatGroup(Chat c, ArrayList<Users> members) {

        try {
            c = serverRemoteObject.createChatGroup(c, members);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //saveeeeee

    /**
     *
     * @param file
     * @param c
     * 
     * send file request to specified user
     * @throws FileNotFoundException
     * @throws RemoteException
     * 
     * 
     * 
     */
    public void takeFile(File file, Chat c) throws FileNotFoundException, RemoteException {

    

        serverRemoteObject.fileRequest(c, HomeController.u);

        /*   String name = file.getName();

            FileInputStream inputstream = new FileInputStream(file);

            RemoteInputStreamServer remoteFileData = new SimpleRemoteInputStream(new BufferedInputStream(inputstream));

            serverRemoteObject.tellOther(remoteFileData, c, name, HomeController.u);

        } 
        
        else {
            if (SignUpController.getController() == null) {

                

                        LoginInController.getController().showInfoDialogue();
                 

            } else {
               

                        SignUpController.getController().showInfoDialogue();
                    }
                

            

        }*/
    }

    /**
     *update friend list when user become online or offline or changed his status
     * @throws IOException
     * 
     * 
     */
    public void updateFriendList() throws IOException {

        if (SignUpController.getController() == null) {

            LoginInController.getController().updateListOfFriends();

        } else {
            SignUpController.getController().updateListOfFriends();

        }

    }

    /**
     *show dialogue box when file request is sent
     * @param sender
     
     */
    public void showDialogue(Users sender) {

        if (SignUpController.getController() == null) {

            LoginInController.getController().showdial(sender);

        } else {
            SignUpController.getController().showdial(sender);

        }

    }

    /**
     *
     * @param sender
     * ignore file request
     * @throws RemoteException
     * 
     */
    public void ignore(Users sender) throws RemoteException {

        serverRemoteObject.ignore(sender);
    }

    /**
     *show dialogue box shows that specified user ignored file request
     */
    public void showRefuseDial() {

        if (SignUpController.getController() == null) {

            LoginInController.getController().showInfoDialogue();

        } else {

            SignUpController.getController().showInfoDialogue();
        }

    }

    /**
     *
     * @param u
     * @throws RemoteException
     */
    public void getFile(Users u) throws RemoteException {


    serverRemoteObject.acceptFile(u);


    }

    /**
     *send file to specified user
     * @throws FileNotFoundException
     * @throws RemoteException
     * 
     * 
     */
    public void sendFile() throws FileNotFoundException, RemoteException {

     File f =LoginInController.getController().f;
     
     Chat c = LoginInController.getController().getCurrent_chat();

            FileInputStream inputstream = new FileInputStream(f);

            RemoteInputStreamServer remoteFileData = new SimpleRemoteInputStream(new BufferedInputStream(inputstream));

            serverRemoteObject.tellOther(remoteFileData, c, f.getName(), HomeController.u);




    }

}
