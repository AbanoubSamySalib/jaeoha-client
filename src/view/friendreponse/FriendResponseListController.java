/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendreponse;

import databaseclasses.Notification;
import view.homescreen.HomeController;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
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
public class FriendResponseListController implements Initializable {

   @FXML
   private ListView responseList;
   
   /**
    * 
    * @param url
    * @param rb
    * 
    * create friend response list  view
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        try {
           HomeController.u = HomeController.model.getResponses(HomeController.u);
         } catch (RemoteException ex) {
         ex.printStackTrace();
       }
        
         ObservableList<Notification> responses = FXCollections.observableArrayList(HomeController.u.getNotification());
        responseList.setItems(responses);
        
        responseList.setCellFactory(new Callback<ListView<Notification>,ListCell<Notification>>() {
            @Override
            public ListCell<Notification> call(ListView<Notification> param) {
                
             return   new ResCellFactory();

            }
        });
        
    }    
    
}
