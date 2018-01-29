/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendsGroupChoose;


import databaseclasses.Chat;
import databaseclasses.Users;
import view.homescreen.HomeController;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 * @param <T>
 */
public class FriendsGroupController <T> implements Initializable {

    @FXML
    private ListView friendsList;
    
    @FXML
    private TextField textArea;
    
    @FXML 
    private ImageView createGroup;
    
    @FXML
    private Label infoLabel;
    
     private ObservableList list;

    /**
     *
     * @return
     */
    public ObservableList getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(ObservableList list) {
        this.list = list;
    }
    
    
    
    /**
     * 
     * @param url
     * @param rb
     * 
     * 
     * create friend group list  view
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        list = FXCollections.observableArrayList(new ArrayList());
        friendsList.setItems(list);
        friendsList.setCellFactory(new Callback<ListView<T>,ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                
             return   new FriendGroupCellFactory();

            }
        });
        
        createGroup.setOnMousePressed(new EventHandler(){
            @Override
            public void handle(Event event) {
             
                if((textArea.getText().trim().equals(""))||(HomeController.groupMembers.size()==0))
                {
                    infoLabel.setText("Please enter required data.");
                //update label with tamam
                }
                else
                {
                    
                     ArrayList<Users> members= new ArrayList();
                 for (Map.Entry<Integer, Users> entry : HomeController.groupMembers.entrySet()) 
                 { 
                     members.add(entry.getValue());
                 }
                 members.add(HomeController.u);
                Chat c=new Chat();
                c.setChatName(textArea.getText());
                c.setChatType(Integer.valueOf(1));
                if(HomeController.model.createChatGroup(c, members)!=null)
                {
                    infoLabel.setText("Group created successfully..");
                    textArea.clear();
                }
                else
                {
                     infoLabel.setText("Error happend.Please try Again!");
                    textArea.clear();
                }
                  
                    
                }
                
            }
            
        });


        
    }    
    
}
