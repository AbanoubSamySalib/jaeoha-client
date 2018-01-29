/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.homescreen;

import model.ClientModel;
import databaseclasses.Chat;
import databaseclasses.Message;
import databaseclasses.Users;
import view.friends.FriendsController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.xml.bind.JAXBException;
import org.controlsfx.control.Notifications;
import view.friendsGroupChoose.FriendsGroupController;

/**
 * FXML Controller class
 *
 * @author abanoub samy
 */
public class HomeController implements Initializable {
    
    
    
    //nnnnnnnn
    @FXML 
    private ColorPicker colorPicker;

    /**
     *
     * @return
     */
    public ColorPicker getColorPicker() {
        return colorPicker;
    }
    
    @FXML
    private MenuButton fontStyle;
    @FXML
    private MenuButton fontSize;
    
    private String font="Arial",size="20";
    //nnnnnnnn
    
    
    

    @FXML

    private ImageView file;

    @FXML

    private ComboBox statusBox;

    @FXML
    private Label userNameLabel;

    @FXML
    private HBox friends;

    @FXML

    private HBox notifications;

    @FXML

    private Circle notifCircle;

    @FXML

    private Label notifLabel;

    @FXML

    private HBox chats;

    @FXML

    private HBox groupChats;

    @FXML

    private Circle chatsCircle;

    @FXML

    private HBox createGroupChat;

    @FXML

    private Circle groupCircle;

    @FXML

    private HBox addFriend;

    @FXML

    private HBox friendRequests;

    @FXML
    private Circle requestCircle;

    @FXML
    private Label reqNotif;

    @FXML

    private HBox friendResponse;

    @FXML

    private Label responsNotif;

    @FXML

    private AnchorPane midAnchor;

    @FXML
    private AnchorPane rightAnchor;

    @FXML

    private Circle profileImage;

    @FXML

    private ImageView savePhoto;

    /**
     *
     */
    public static ClientModel model;

    /**
     *
     */
    public static Users u;

    /**
     *
     */
    public Stage stage;

    MediaPlayer mediaPlayer;

    String path;

    @FXML

    private TextField messageInput;

    @FXML

    private ListView messageList;
    
    
    

    @FXML

    private Circle im;

    private ObservableList list;

    private Chat Current_chat;
    
    /**
     *
     */
    public File f;

    private String current = null;

    //esraa================
    private ArrayList<Users> friendsList;
    private ArrayList<Chat> recentChatsArr;
    private ArrayList<Chat> groupChatsArr;

    /**
     *
     */
    public static HashMap<Integer, Users> groupMembers;
    //=====================

    
    
    
    
    
    //nnnnnnnnnnnnnnnnnnnnn

    /**
     *
     * @return
     */
    public ListView getMessageList() {
        return messageList;
    }
    
    //nnnnnnnnnnnnnnnnnnnnn
    
    /**
     * 
     * @param url
     * @param rb
     * 
     * create home screen view
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Current_chat=new Chat();
        
        
        //nnnnnnn
       ArrayList  <MenuItem> items=new ArrayList<>();
       for(int i=0;i<javafx.scene.text.Font.getFamilies().size();i++)
       {
           MenuItem item=new MenuItem(javafx.scene.text.Font.getFamilies().get(i));
           item.setOnAction(new EventHandler(){
           @Override
           public void handle(Event event) {
               font=item.getText();
           }
           
       });
           items.add(item);
       }
       fontStyle.getItems().addAll(items);
      
       ArrayList  <MenuItem> itemsFontSize=new ArrayList<>();
       for(int i=5;i<80;i+=5)
       {
           MenuItem item=new MenuItem(Integer.toString(i));
           item.setOnAction(new EventHandler(){
           @Override
           public void handle(Event event) {
               size=item.getText();
           }
           
       });
           itemsFontSize.add(item);
       }
       fontSize.getItems().addAll(itemsFontSize);
       //fontSize.setText("20");
      
       //nnnnnn
        
        
        
        
        

        groupMembers = new HashMap<Integer, Users>();

        try {

            try {
                path = new File(".").getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            statusBox.getItems().addAll("Available", "Busy", "Away");
//            if (u != null) {
//                System.out.println("view.homescreen.HomeController.initialize()");
//                statusBox.getSelectionModel().select(u.getStatus());
//
//            } else {
//                statusBox.getSelectionModel().select(0);
//
//            }


            file.setImage(new Image("file:///" + path + "/src/others/images/clip.png"));
            savePhoto.setImage(new Image("file:///" + path + "/src/others/images/save.png"));

            notifCircle.setFill(new ImagePattern(new Image("file:///" + path + "/src/others/images/notifications.png")));

            requestCircle.setFill(new ImagePattern(new Image("file:///" + path + "/src/others/images/request.png")));

            groupCircle.setFill(new ImagePattern(new Image("file:///" + path + "/src/others/images/groupc.png")));

            chatsCircle.setFill(new ImagePattern(new Image("file:///" + path + "/src/others/images/chats.png")));
            messageInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (event.getCode() == KeyCode.ENTER) {

                        sendMessage();

                    }
                }

            });
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        list = FXCollections.observableArrayList(new ArrayList());
       
        messageList.setItems(list);
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {

                return new MessageCellFactory();

            }
        });
        
        
        
        
        //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

    }

    /**
     * call save messages method in client model 
     * to save message in xml file
     * @throws RemoteException
     * @throws JAXBException
     * @throws FileNotFoundException
     * 
     
     */
    @FXML

    public void saveMessages() throws RemoteException, JAXBException, FileNotFoundException {

        model.saveMessages(Current_chat);

    }

    /**
      * call change status method in client model
     * to change user status
     * @throws RemoteException

     */
    @FXML
    public void statusBoxChanged() throws RemoteException {

        model.changeStatus(u, statusBox.getValue().toString());

    }

    /**
     * show profile details to user
     * @throws IOException
     * 
     
     */
    @FXML

    public void userNamePressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "userNamedPressed";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/profile/Profile.fxml"));

        Node node = loader.load();

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);

    }

    /**
     * show friends list to user
     * @throws IOException

     */
    @FXML

    public void friendsPressed() throws IOException {
        
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "friendPressed";
        midAnchor.getChildren().clear();

        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
        FriendsController controller=loader.getController();
        controller.getList().clear();
        controller.setFreiendCellFactoryController(this);
        ArrayList<Users> friendsArr = model.getMyFriends(u);
        for (int i = 0; i < friendsArr.size(); i++) {
            controller.getList().add(friendsArr.get(i));
        }

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);
          //nnnnnnnnn
        messageList.setVisible(false);
        
        
        
     /*   FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
        ((FriendsController) loader.getController()).getList().clear();
        ArrayList<Users> friendsArr = model.getMyFriends(u);
        for (int i = 0; i < friendsArr.size(); i++) {
            ((FriendsController) loader.getController()).getList().add(friendsArr.get(i));
        }

        // midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);*/

    }

    /**
     * show notification list to user
     * @throws IOException
     
     */
    @FXML

    public void notificationsPressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "notificationPressed";

        notifLabel.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/notifications/Notificaions.fxml"));

        Node node = loader.load();

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);
    }

    /**
     * show recent chats list to user
     * @throws IOException
     * 

     */
    @FXML

    public void chatsPressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "chatsPressed";

        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
         FriendsController controller=loader.getController();
        controller.getList().clear();
        controller.setFreiendCellFactoryController(this);
        ArrayList<Chat> recentChats = model.get_allRecentChats(u);
        for (int i = 0; i < recentChats.size(); i++) {
           controller.getList().add(recentChats.get(i));
        }

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);
        
          //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn
        
        
        
        
      /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
        ((FriendsController) loader.getController()).getList().clear();
        ArrayList<Chat> recentChats = model.get_allRecentChats(u);
        for (int i = 0; i < recentChats.size(); i++) {
            System.err.println("rc--------------------------------->:" + recentChats.get(i).getChatId());
            ((FriendsController) loader.getController()).getList().add(recentChats.get(i));
        }

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);*/

    }

    /**
       * show group chat list to user
     * @throws IOException
   
     */
    @FXML

    public void groupChatsPressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "groupChatsPressed";
     
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
        FriendsController controller=loader.getController();
        controller.getList().clear();
        controller.setFreiendCellFactoryController(this);
        ArrayList<Chat> recentChats = model.get_allGroupChats(u);
        for (int i = 0; i < recentChats.size(); i++) {
            controller.getList().add(recentChats.get(i));
        }

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);
          //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn
        
        
        
        
        /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friends/Friends.fxml"));
        Node node = loader.load();
        ((FriendsController) loader.getController()).getList().clear();
        ArrayList<Chat> recentChats = model.get_allGroupChats(u);
        for (int i = 0; i < recentChats.size(); i++) {
            System.err.println("rc--------------------------------->:" + recentChats.get(i).getChatId());
            ((FriendsController) loader.getController()).getList().add(recentChats.get(i));
        }

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);*/
    }

    /**
     *
     * create group chat 
     * 
     */
    @FXML

    public void createGroupChatPressed() {
        
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "createGroupChatPressed";

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friendsGroupChoose/Friends.fxml"));
            Node node = loader.load();
            ((FriendsGroupController) loader.getController()).getList().clear();
            ArrayList<Users> friendsArr = model.getMyFriends(u);//hnaaa
            for (int i = 0; i < friendsArr.size(); i++) {
                ((FriendsGroupController) loader.getController()).getList().add(friendsArr.get(i));
            }
            midAnchor.getChildren().clear();
            midAnchor.getChildren().add(node);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
      * add new friend
     * @throws IOException

     */
    @FXML

    public void addFriendPressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "addFriendPressed";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addfriend/AddFriend.fxml"));

        Node node = loader.load();

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);

    }

    /**
      * show friend request list to user
     * @throws IOException
    
     */
    @FXML

    public void friendRequestsPressed() throws IOException {

        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn
        current = "friendRequestsPressed";

        reqNotif.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friendrequests/RequestsList.fxml"));

        Node node = loader.load();

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);

    }

    /**
      * show friend response list to user 
     * show if user accepted your request and become your friend
     * @throws IOException
    
     */
    @FXML

    public void friendResponsePressed() throws IOException {
        
         //nnnnnnnnn
        messageList.setVisible(false);
        //nnnnnnnnn

        current = "friendResponsePressed";

        responsNotif.setVisible(false);

        System.out.println("friend frienddddddddddd presssssssssed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/friendreponse/FriendResponseList.fxml"));

        System.out.println("friend response presssssssssed");
        Node node = loader.load();

        System.out.println("node");

        midAnchor.getChildren().clear();
        midAnchor.getChildren().add(node);

    }

    /**
     *
     * @param u
     * show desktop notification when friend become online
     */
    public void showPopUpNotification(Users u) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineNotif.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                    OnlineNotifController cont = loader.getController();
                    cont.setUserName(u);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications notifBuilder = Notifications.create()
                        .title("Online Notification")
                        .graphic(node)
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));

                notifBuilder.show();
            }
        });

    }

    /**
     *show notification circle 
     * and sound when new notification recieved
     */
    @FXML
    public void newAdminNotification() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                notifLabel.setVisible(true);
                String musicFile = null;

                musicFile = path + "/src/others/sounds/not.mp3";

                Media sound = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();

                System.out.println("circle is red");

            }
        });

    }

    /**
     *show notification circle 
     * and sound when new friend request recieved
     */
    public void newRequestNotif() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                reqNotif.setVisible(true);
                String musicFile = null;

                musicFile = path + "/src/others/sounds/not.mp3";

                Media sound = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();

                System.out.println("circle is red");

            }
        });
    }

    /**
     *show notification circle 
     * and sound when new friend response recieved
     */
    public void newResponseNotif() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                responsNotif.setVisible(true);
                String musicFile = null;

                musicFile = path + "/src/others/sounds/not.mp3";

                Media sound = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(sound);

                mediaPlayer.play();

                System.out.println("circle is red");

            }
        });
    }

    /**
     *
     * @param c
     * 
     */
    public void setC(ClientModel c) {
        HomeController.model = c;
    }

    /**
     *
     * @param u
     * 
     */
    public void setU(Users u) {

        HomeController.u = u;
        
         if (u != null) {
                System.out.println("view.homescreen.HomeController.initialize()");
                statusBox.getSelectionModel().select(u.getStatus());

            } else {
                statusBox.getSelectionModel().select(0);

            }
        
        if (userNameLabel == null) {
            System.out.println("user name =null");
        }
        userNameLabel.setText(u.getUserName());
     //   profileImage.setFill(new ImagePattern(new Image(new ByteArrayInputStream(u.getImage()))));
     
     
     
   //   profileImage.setFill(new ImagePattern(new Image("file:///" + path + "/src/others/images/avatar.png")));
     
     

    }

    /**
     * @return the u
     */
    public Users getU() {
        return u;
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
     * @param messages
     * show  messages in list view
     * @throws IOException
    
     */
    public void showMessages(ArrayList<Message> messages) throws IOException {

        list.clear();

        for (int i = 0; i < messages.size(); i++) {
            list.add(messages.get(i));
        }

    }

    /**
     * send new message typed in text field to specified user when enter key is pressed  
     */
    public void sendMessage() {

        if ((messageInput != null) && (!messageInput.getText().trim().equals(""))) {
            Message message = new Message();
            message.setChatId(Current_chat.getChatId());
            message.setMessageText(messageInput.getText());
            message.setSenderId(u.getId());
            
            
            //nnnnnnnnnnnnnnnnnnn
            message.setMessageColor(colorPicker.getValue().toString());
            message.setFontSize(Integer.parseInt(size));
            message.setFontType(font);
            //nnnnnnnnnnnnnnnnnnn
            message.setMessageTime(Timestamp.from(Instant.now()));
            try {
                //kmly l lon wl font wl klam da
                model.sendMessage(message);
            } catch (RemoteException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            messageInput.clear();
        }
    }

    /**
     *
     * @param message
     * add new message to list view 
     */
    public void addnewMessage_show(Message message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

               if(Current_chat.getChatId()!=null)
               {
                   if (message.getChatId().intValue() == Current_chat.getChatId().intValue()) {
                    list.add(message);
                }
               }

            }
        });

    }

    /**
     * @return the Current_chat
     */
    public Chat getCurrent_chat() {
        return Current_chat;
    }

    /**
     * @param Current_chat the Current_chat to set
     */
    public void setCurrent_chat(Chat Current_chat) {
        this.Current_chat = Current_chat;
    }

    //saveeeee

    /**
      * open file chooser to choose file to send to specified user
     * @throws FileNotFoundException
     * @throws IOException
     * 
    
     */
    @FXML

    public void filePressed() throws FileNotFoundException, IOException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        
        f= file;

        //smsm
        if (file != null) {
            model.takeFile(file, Current_chat);

        }

    }

    /**
     *
     */
    public void progress() {

        /*
        timeline.setCycleCount(Timeline.INDEFINITE);
         
        final KeyValue kv = new KeyValue(stroke, 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);
         
        timeline.getKeyFrames().add(kf);
        timeline.play();        
         
        VBox root = new VBox(3);
         
        StackPane progressIndicator = new StackPane();
         
        Rectangle bar = new Rectangle(350,13);
        bar.setFill(Color.TRANSPARENT);
        bar.setStroke(Color.WHITE);
        bar.setArcHeight(15);
        bar.setArcWidth(15);
        bar.setStrokeWidth(2);
         
        Rectangle progress = new Rectangle(342,6);
        progress.setFill(Color.WHITE);
        progress.setStroke(Color.WHITE);
        progress.setArcHeight(8);
        progress.setArcWidth(8);
        progress.setStrokeWidth(1.5);
        progress.getStrokeDashArray().addAll(3.0,7.0,3.0,7.0);
        progress.strokeDashOffsetProperty().bind(stroke);
         
         
        progressIndicator.getChildren().add(progress);
        progressIndicator.getChildren().add(bar);
         
        root.getChildren().add(progressIndicator);
         
        Text label = new Text("Loading...");
        label.setFill(Color.WHITE);
         
        root.getChildren().add(label);
         
        getChildren().add(root);*/
    }

    //smsm

    /**
     *update list of friends when user became online or offline 
     * or user's status  changed
     * @throws IOException
     * 
     * 
     */
    public void updateListOfFriends() throws IOException {

        System.out.println("update list");

        System.out.println(current);
        if(current!=null)
        {
        if (current.equals("friendPressed")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        friendsPressed();
                    } catch (IOException ex) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            System.out.println("list updated");
        }
        }
    }
 
    /**
     *
     * @param k
     * show dialogue box to accept or cancel file request sent by friend
     */
    public void showdial(Users k) {
        
        

       Platform.runLater(() -> {
           
           System.out.println("el ly fate7 el homae controller "+HomeController.u.getUserName());
           
            System.out.println("elly 3ayz yb3t el file "+k.getUserName());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("File Request");
            alert.setHeaderText(k.getUserName() + " wants to send you file");
            alert.setContentText("Accept?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) 
            {
                try {
                    model.getFile(k);
                } catch (RemoteException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else
            {
                try {
                    
                    System.out.println("cancelled");
                    model.ignore(k);
                } catch (RemoteException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

      });

        
    }
    
    /**
     *show dialogue box when friend refuse file requestz
     */
    public void showInfoDialogue() {

        Platform.runLater(() -> {

            System.out.println("el user bta3 el home controller hna"+HomeController.u.getUserName());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("request refused");
            alert.showAndWait();
        });
        

    }

}
