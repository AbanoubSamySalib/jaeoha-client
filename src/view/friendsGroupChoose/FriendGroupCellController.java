/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendsGroupChoose;

import databaseclasses.Users;
import view.homescreen.HomeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 * @param <T>
 */
public class FriendGroupCellController<T> implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML

    Label userName;

    @FXML
    Label userStatus;

    @FXML
    CheckBox checkBox;

    // Users friend;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }

    /**
     *
     * @param item
     */
    public void setItem(T item) {

        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (checkBox.isSelected()) {
                    System.err.println("user:"+((Users)item).getId());
                    HomeController.groupMembers.put(((Users)item).getId(), (Users)item);
                } else {
                    HomeController.groupMembers.remove(((Users)item).getId());
                }
             }
         });
        Users friend = (Users) item;
            userName.setText(friend.getUserName());
           
    }

}
