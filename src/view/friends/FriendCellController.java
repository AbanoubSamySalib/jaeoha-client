/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friends;

import databaseclasses.Chat;
import databaseclasses.Notification;
import databaseclasses.Users;
import view.homescreen.HomeController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 * @param <T>
 */
public class FriendCellController<T> implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML

    Label userName;

    @FXML
    Label userStatus;

    @FXML

    Circle onlineCircle;

    // Users friend;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     *
     * @param item
     */
    public void setItem(T item) {

        if (item instanceof Chat)//chat
        {
            Chat c = (Chat) item;
            if (c.getChatType().intValue() == 0)//individual
            {
                Users friend = null;
                ArrayList<Users> users = HomeController.model.getChatUsers(c);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getId().intValue() != HomeController.u.getId().intValue())//yb2a l friend
                    {
                        friend = users.get(i);
                    }
                }
                userName.setText(friend.getUserName());
                if (friend.getActive() == 1) {
                    userStatus.setText(friend.getStatus());
                    onlineCircle.setFill(Color.GREEN);
                } else {
                    onlineCircle.setFill(Color.RED);
                }
            } else if (c.getChatType().intValue() == 1)//grp
            {
                userName.setText(c.getChatName());

            }
        } else if (item instanceof Users)//userr
        {
            Users friend = (Users) item;
            userName.setText(friend.getUserName());
            if (friend.getActive() == 1) {
                userStatus.setText(friend.getStatus());
                onlineCircle.setFill(Color.GREEN);
            } else {
                onlineCircle.setFill(Color.RED);
            }
        }
    }

}
