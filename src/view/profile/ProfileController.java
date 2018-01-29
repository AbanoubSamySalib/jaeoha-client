/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.profile;

import view.login.LoginInController;
import view.homescreen.HomeController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class ProfileController implements Initializable {

    @FXML
    Circle img;

    @FXML

    private Button edit;

    @FXML
    private TextField userName;

    @FXML

    private TextField userEmail;

    @FXML

    private ComboBox gender;

    @FXML
    private ComboBox country;

    @FXML
    private Button signout;
    
    /**
     * 
     * @param url
     * @param rb 
     * 
     * create user profile view
     * 
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userName.setText(view.homescreen.HomeController.u.getUserName());

        userEmail.setText(view.homescreen.HomeController.u.getEmail());

        gender.getItems().addAll("male", "female");

        gender.getSelectionModel().select(view.homescreen.HomeController.u.getGender());

        country.getItems().addAll("Alex", "cairo", "America");

        country.getSelectionModel().select(view.homescreen.HomeController.u.getCountry());

    }

    /**
     *
     * @throws FileNotFoundException
     * @throws RemoteException
     */
    @FXML

    public void imgPressed() throws FileNotFoundException, RemoteException {

//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("IMG files (*.jpg)", "*.jpg");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        File file = fileChooser.showOpenDialog(stage);
//
//        if (file != null) {
//
//            InputStream inputstream = new FileInputStream(file);
//              RemoteInputStreamServer remoteFileData = new SimpleRemoteInputStream(new BufferedInputStream(new FileInputStream(file)));
//            
//            HomeController.u.setRis(remoteFileData);
//            HomeController.model.updatemyData(HomeController.u);
//            Image im = new Image(inputstream);
//
//            img.setFill(new ImagePattern(im));
//
//        }
    }

    /**
     *make all fields editable except email and user can edit data 
     * @throws RemoteException
     * 
     * 
     */
    @FXML

    public void editPressed() throws RemoteException {

        if (edit.getText().equals("Edit")) {
            userName.setEditable(true);
            gender.setDisable(false);
            country.setDisable(false);
            edit.setText("Save");
        } else {

            HomeController.u.setUserName(userName.getText());
            HomeController.u.setGender(gender.getSelectionModel().getSelectedItem().toString());
            HomeController.u.setCountry(country.getSelectionModel().getSelectedItem().toString());
            HomeController.model.updatemyData(HomeController.u);
            userName.setEditable(false);
            gender.setDisable(true);
            country.setDisable(true);

            edit.setText("Edit");

        }
    }

    /**
     *sign user out of database and application
     * @throws IOException
     * 
     */
    @FXML

    public void SignOut() throws IOException {
        
      //smsm  
        HomeController.model.signOut(HomeController.u);
        //smsm

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/LoginIn.fxml"));

        Parent root = loader.load();

        LoginInController controller = loader.getController();

        controller.setStage(LoginInController.stage);

        Scene scene = new Scene(root);

        LoginInController.stage.setScene(scene);
    }

}
