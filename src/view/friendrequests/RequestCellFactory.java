/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendrequests;

import databaseclasses.Users;
import view.friends.FriendCellController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 *
 * @author abanoub samy
 */
public class RequestCellFactory extends ListCell<Users> {
    
    
    @Override
    protected void updateItem(Users item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {

            setGraphic(null);
        } else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReuqestCell.fxml"));

                Node node = loader.load();

                ReuqestCellController controller = loader.getController();
                controller.setItem(item);

                setGraphic(node);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
    
}
