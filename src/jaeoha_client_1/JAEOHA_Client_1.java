/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaeoha_client_1;

import ConnectToServer.Connect;
import view.login.LoginInController;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rmi.interfaces.ServerInterface;

/**
 *
 * @author esraa
 */
public class JAEOHA_Client_1 extends Application {

    Registry registry;
    ServerInterface serverRemoteObject;

    /**
     *
     * @throws NotBoundException
     */
    public JAEOHA_Client_1() throws NotBoundException {

    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = null;
        Scene scene = null;
        try {
            new Connect();
            loader = new FXMLLoader(getClass().getResource("/view/login/LoginIn.fxml"));
            Parent root = null;

            root = loader.load();
            LoginInController controller = loader.getController();
            scene = new Scene(root);
            controller.setStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception r) {
            loader = new FXMLLoader(getClass().getResource("/view/serverDown/serverDown.fxml"));

            Parent root = null;

            root = loader.load();

            scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
            
            
            System.out.println("server not found");
        }
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });


//        Parent root = null;
//       // System.err.println("c:"+getClass().toString());
//       // FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/LoginIn.fxml"));
//
//        root = loader.load();
//        LoginInController controller = loader.getController();
//        Scene scene = new Scene(root);
//        controller.setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
