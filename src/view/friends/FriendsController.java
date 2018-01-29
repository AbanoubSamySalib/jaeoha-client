/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friends;


import view.homescreen.HomeController;
import java.net.URL;
import java.util.ArrayList;
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
 * @param <T>
 */
public class FriendsController <T> implements Initializable {

    @FXML
    
    private ListView friendsList;
    
    //nnnnnnnnnnnnnn
     private  HomeController  controllerH=new HomeController();

    /**
     *
     * @param c
     */
    public   void setFreiendCellFactoryController( HomeController c)
        {
           controllerH=c;
        }
    //nnnnnnnnnnnnn
    
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
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       list = FXCollections.observableArrayList(new ArrayList());
        friendsList.setItems(list);
        friendsList.setCellFactory(new Callback<ListView<T>,ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                
             FriendCellFactory f =new FriendCellFactory();
                f.setFreiendCellFactoryController(controllerH);
             return f  ;

            }
        });


        
    }    
    
}
