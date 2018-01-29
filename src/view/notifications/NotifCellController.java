/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.notifications;

import databaseclasses.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class NotifCellController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label notif;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setItem(Notification item) {
        
        notif.setText(item.getNotifText());
        
        
        
    }
    
}
