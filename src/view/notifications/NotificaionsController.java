/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.notifications;

import databaseclasses.Notification;
import databaseclasses.Users;
import view.friends.FriendCellFactory;
import view.homescreen.HomeController;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class NotificaionsController implements Initializable {

   @FXML
   
   private ListView notificationListView;
   
   /**
    * 
    * @param url
    * @param rb 
    * create notifications list view
    * 
    */
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO

           HomeController.u = HomeController.model.getNotifications(HomeController.u);
       } catch (RemoteException ex) {
           Logger.getLogger(NotificaionsController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
         ObservableList<Notification> notifications = FXCollections.observableArrayList(HomeController.u.getAdminNotification());
       
     
       
       notificationListView.setItems(notifications);
        
        notificationListView.setCellFactory(new Callback<ListView<Notification>,ListCell<Notification>>() {
            @Override
            public ListCell<Notification> call(ListView<Notification> param) {
                
             return   new NotifCellFactory();

            }
        });
        
        
    }    
    
}
