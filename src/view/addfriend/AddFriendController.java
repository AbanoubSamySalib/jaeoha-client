/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.addfriend;

import databaseclasses.Users;
import view.homescreen.HomeController;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class AddFriendController implements Initializable {

    @FXML

    private TextField search;

    @FXML

    private HBox result;

    @FXML

    private Label temp;

    @FXML

    private Label userName;

    @FXML

    private Circle profileImage;

    @FXML

    private Button add;
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.ENTER) {

                    search();

                }
            }

        });
    }

    /**
     * search for friend by typing his email 
     */
    public void search() {
        
        temp.setVisible(false);

        try {
            HomeController.u.setFriends(HomeController.model.getMyFriends(HomeController.u));
            //fadely check if hwa friend asln 
            Users retrievedUser = view.homescreen.HomeController.model.searchForUser(HomeController.u, search.getText());

            if (retrievedUser != null) {

                boolean checkIfRequested = HomeController.model.checkRequestedOrNot(HomeController.u, retrievedUser);

                if (checkIfRequested) {

                    temp.setText("user already requested");
                    temp.setVisible(true);

                } 
                else if (HomeController.model.isFriend(HomeController.u, retrievedUser)) {
                    
                    temp.setText("Already added");
                    temp.setVisible(true);
                    

                } else if (Objects.equals(retrievedUser.getId(), HomeController.u.getId())) {

                    temp.setText("This is your email");
                    temp.setVisible(true);
                } else {

                    userName.setText(retrievedUser.getUserName());

                    result.setVisible(true);

                    add.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                boolean added = HomeController.model.addUser(HomeController.u, retrievedUser);
                                if (added) {

                                    result.setVisible(false);

                                    temp.setText("request has been sent");
                                    temp.setVisible(true);

                                }

                            } catch (RemoteException ex) {
                                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                }

            } else {

            }
        } catch (RemoteException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
