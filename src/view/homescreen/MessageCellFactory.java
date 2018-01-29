/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.homescreen;

import databaseclasses.Message;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author abanoub samy
 */
public class MessageCellFactory extends ListCell<Message> {

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {

            setGraphic(null);
        } else {
           try {

               
               HBox h =new HBox();
                Text t = new Text(item.getMessageText());
                t.setFill(Color.web(item.getMessageColor()));
                t.setFont(new Font(item.getFontType(),item.getFontSize()));
                TextFlow w = new TextFlow(t);
                w.setPrefWidth(200);
                h.getChildren().add(w);

                if (!Objects.equals(item.getSenderId(), HomeController.u.getId())) {
                    w.setId("messageLayout2");
                   
                     h.setAlignment(Pos.CENTER_RIGHT);
                    setGraphic(h);
                   
                } else {
                    // ana aly ba3ta 
                    w.setId("messageLayout1");
                    
                    h.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(h);
                    

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
