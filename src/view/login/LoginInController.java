package view.login;

import view.signup.SignUpController;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author abanoub samy
 */
public class LoginInController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML

    private TextField email;

    @FXML

    private PasswordField password;

    @FXML

    private Label informLabel;

    @FXML

    private Label signUpLabel;

    @FXML
    private Circle logo;

  private static HomeController controller;

    /**
     *
     */
    public  static Stage stage;

    ClientModel c = new ClientModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   System.err.println("ksdklsdj");
     //   System.err.println(getClass()+"../../others/images/logo.png");
     //   System.err.println(getClass().getResource("../../others/images/logo.png").toString());
     //   ImagePattern pattern = new ImagePattern(new Image((getClass().getResource("../../others/images/logo.png")).toString()));//getclass btgeb mkan l ana feh now 
     //   logo.setFill(pattern);

    }

    /**
     *sign in user and change scene to home screen 
     * @throws RemoteException
     * @throws IOException
     * 
     */
    @FXML
    public void signIn() throws RemoteException, IOException {

        if (email.getText().trim().equals("") || password.getText().trim().equals("")) {
            informLabel.setText("All fields must be entered");
        } else {

            String[] data = {email.getText(), password.getText()};

            Users u = c.signIn(data);

            if (u == null) {

                informLabel.setText("user not exist .. sign up first");

            } else {

                
                
                //han2l 3la el main page w adylo el user
                
                
                goTOHomePage(u);
               
                System.out.println(u.getId());
                System.out.println(u.getUserName());
                System.out.println(u.getActive());

            }
        }

    }

    /**
     *  change scene to sign up screen if user is new

     * @throws IOException
     */
    @FXML

    public void signUpFirst() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup/SignUp.fxml"));

        Parent root = loader.load();

        SignUpController controller = loader.getController();

       
        controller.setStage(stage);
        controller.setC(this.c);
        

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
     * 
     * @param u
     * change scene to home screen

     * @throws IOException 
     */
    private void goTOHomePage(Users u) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homescreen/Home.fxml"));
       controller = new HomeController();
       loader.setController(controller);
        Parent root = loader.load();
      
        controller.setStage(stage);
        controller.setC(this.c);
        controller.setU(u);
      //  c.setController(controller);

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
