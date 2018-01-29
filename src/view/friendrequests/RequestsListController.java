/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendrequests;

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
public class RequestsListController implements Initializable {

    @FXML
    
    private ListView requestsList;
    
    /**
     * 
     * @param url
     * @param rb 
     * 
     * create friend request list  view
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        try {
            HomeController.u = HomeController.model.getfriendRequests(HomeController.u);
        } catch (RemoteException ex) {
            Logger.getLogger(RequestsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          ObservableList<Users> Requests = FXCollections.observableArrayList(HomeController.u.getFriendRequests());
       
       
       
        requestsList.setItems(Requests);
        
        requestsList.setCellFactory(new Callback<ListView<Users>,ListCell<Users>>() {
            @Override
            public ListCell<Users> call(ListView<Users> param) {
                
             return   new RequestCellFactory();

            }
        });
        
        
        
        
    }    
    
}
