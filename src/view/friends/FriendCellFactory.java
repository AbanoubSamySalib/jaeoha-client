/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friends;

import view.login.LoginInController;
import databaseclasses.Chat;
import databaseclasses.Message;
import databaseclasses.Users;
import view.homescreen.HomeController;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 *
 * @author abanoub samy
 * @param <T>
 */
public class FriendCellFactory<T> extends ListCell<T> {
    
    
    private  HomeController  controllerH=new HomeController();

    /**
     *
     * @param c
     */
    public   void setFreiendCellFactoryController( HomeController c)
        {
           controllerH=c;
        }

    //esraaaaa
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
        } else {
            try {
                if (item instanceof Chat) {
                    Chat c = (Chat) item;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendCell.fxml"));
                    Node node = loader.load();
                    FriendCellController controller = loader.getController();
                    controller.setItem(item);
                    setGraphic(node);
                    //hnaa t3deelesraaaaaaaaaaaaaaaaaaaa
                    this.setOnMousePressed(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            try {
                                  LoginInController.getController().setCurrent_chat(c);
                                ArrayList<Message> messages = HomeController.model.getChatMessage(c);
                                HomeController.model.showMessages(messages);
                                
                                
                                //nnnnnnnnn
                                    controllerH.getMessageList().setVisible(true);
                                 //nnnnnnnnn
                            } catch (RemoteException ex) {
                                Logger.getLogger(FriendCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(FriendCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    });
                } else if (item instanceof Users) {
                    Users u = (Users) item;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendCell.fxml"));
                    Node node = loader.load();
                    FriendCellController controller = loader.getController();
                    controller.setItem(item);
                    setGraphic(node);
                    this.setOnMousePressed(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            Chat chat;
                            try {
                                chat = HomeController.model.get_individualChat(HomeController.u, u);

                                LoginInController.getController().setCurrent_chat(chat);

                                ArrayList<Message> messages = HomeController.model.getChatMessage(chat);

                                HomeController.model.showMessages(messages);

                                
                                //nnnnnnnnn
                                    controllerH.getMessageList().setVisible(true);
                                 //nnnnnnnnn
                                
                            } catch (RemoteException ex) {
                                Logger.getLogger(FriendCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(FriendCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    });
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    //abanouuub
//    @Override
//    protected void updateItem(Users item, boolean empty) {
//        super.updateItem(item, empty);
//
//        if (item == null || empty) {
//
//            setGraphic(null);
//        } else {
//            try {
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendCell.fxml"));
//
//                Node node = loader.load();
//
//                FriendCellController controller = loader.getController();
//                controller.setItem(item);
//
//                setGraphic(node);
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//
//    }
}
