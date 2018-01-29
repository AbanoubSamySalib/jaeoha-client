/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendrequests;


import databaseclasses.Users;
import view.homescreen.HomeController;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class ReuqestCellController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label userName;

    @FXML

    Button accept;

    @FXML

    Button delete;
    
    
    @FXML
            
    HBox hbox;
    
    @FXML
    Label label;
    
    
    
    Users requester;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     *call acceptRequest method of model
     * @throws RemoteException
     * 
     */
    @FXML
    public void acceptRequest() throws RemoteException {
        HomeController.model.acceptRequest(HomeController.u, requester);
       
        
        
        hbox.getChildren().clear();
        label.setVisible(true);
        label.setText("Friend Added");
        
        
     

    }

    /**
     *
     * @throws RemoteException
     */
    @FXML

    public void deleteRequest() throws RemoteException {

        System.out.println("abanpub hena");
      HomeController.model.deleteRequest(HomeController.u, requester);
              
              
              System.out.println("abanpub hena bardo");
        hbox.getChildren().clear();
        label.setVisible(true);
        label.setText("Request Deleted");
        
        
      
        

    }

    void setItem(Users item) {

        requester = item;
        
        userName.setText(requester.getUserName());
        
        

    }

}
