package view.signup;

import view.login.LoginInController;
import model.ClientModel;
import databaseclasses.Users;
import view.homescreen.HomeController;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author abanoub samy
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField userNameTF;

    @FXML

    private TextField emailTF;

    @FXML
    private PasswordField passwordTF;

    @FXML

    private PasswordField repasswordTF;

    @FXML

    private ComboBox gender;

    @FXML

    private Button signUpB;

    @FXML

    private Label informLabel;

    @FXML
    private Circle logo;

    Stage stage;

    ClientModel c;

    private static HomeController controller;

    /**
     *
     * @return
     */
    public ClientModel getC() {
        return c;
    }

    /**
     *
     * @param c
     */
    public void setC(ClientModel c) {
        this.c = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ImagePattern pattern = new ImagePattern(new Image("logo.png"));
        //logo.setFill(pattern);

        gender.getItems().addAll("male", "female");
        gender.getSelectionModel().select("male");

    }

    /**
     *sign up user and change scene to home screen
     * @throws RemoteException
     * @throws IOException
     * 
     */
    @FXML

    public void signUp() throws RemoteException, IOException {

        if (userNameTF.getText().equals("") || emailTF.getText().trim().equals("") || passwordTF.equals("")
                || repasswordTF.getText().equals("")) {

            informLabel.setText("All fields must be entered");
        } else {

            if (!passwordTF.getText().equals(repasswordTF.getText())) {
                informLabel.setText("Password not Confirmed ");
            } else {

                String[] data = {userNameTF.getText(), emailTF.getText(), passwordTF.getText(), gender.getSelectionModel().getSelectedItem().toString()};

                Users u = c.signUp(data);

                if (u == null) {
                    informLabel.setText(" Email is already exist");

                } else {
//han2l 3la el main page w 2b3t el user

                    goTOHomePage(u);
                    System.out.println(u.getUserName());
                    System.out.println(u.getActive());

                }
            }

        }

    }

    /**
     *change scene to log in screen
     * @throws IOException
     * 
     */
    @FXML
    public void logInScene() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/LoginIn.fxml"));

        Parent root = loader.load();

        LoginInController controller = loader.getController();

        controller.setStage(stage);

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    /**
     *
     * @param stage
     */
    public void setStage(Stage stage) {

        this.stage = stage;
    }

    
    
    /**
     * change scene to home screen
     * @param u
     * @throws IOException 
     * 
     */
    private void goTOHomePage(Users u) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homescreen/Home.fxml"));

        controller = new HomeController();
        loader.setController(controller);
        Parent root = loader.load();

        // HomeController  controller = loader.getController();
        controller.setStage(stage);
        controller.setC(this.c);
        controller.setU(u);
       //

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    /**
     *
     * @return
     */
    public static HomeController getController() {
        return controller;
    }

}
