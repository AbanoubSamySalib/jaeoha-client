/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendreponse;

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
public class ResCellController implements Initializable {

    @FXML
    
    private Label resNotif;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    /**
     * 
     * @param item 
     * show notification to user in label
     * 
     */
    
     void setItem(Notification item) {
        
        resNotif.setText(item.getNotifText());
        
        
        
    }
    
}
