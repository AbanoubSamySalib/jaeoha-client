/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.friendreponse;

import databaseclasses.Notification;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import view.notifications.NotifCellController;

/**
 *
 * @author abanoub samy
 */
public class ResCellFactory extends ListCell<Notification> {
    
    
      
    @Override
    protected void updateItem(Notification item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {

            setGraphic(null);
        } else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ResCell.fxml"));

                Node node = loader.load();

                ResCellController controller = loader.getController();
                controller.setItem(item);

                setGraphic(node);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
    
    
}
