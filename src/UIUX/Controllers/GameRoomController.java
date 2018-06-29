package UIUX.Controllers;

import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import ClientAndHandlerCommunication.Commands.RecieveChatCommand;
import ClientAndHandlerCommunication.Commands.SendChatCommand;
import ClientAndHandlerCommunication.Commands.madeAMoveCommand;
import ClientAndHandlerCommunication.Responses.JoinedGameResponse;
import Enums.ChatChannelType;
import Enums.JoinerType;
import Game.Match;
import Game.Profile;
import NetworkShit.ClientSide.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameRoomController extends ParentController implements Initializable  {
    @FXML
    TextField chatInput;
    @FXML
    Label hostLabel;
    @FXML
    Label guestLabel;
    @FXML
    VBox audienceBox;

    @FXML
    RadioButton rivalSelect;
    @FXML
    RadioButton audienceSelect;
    @FXML
    VBox audienceChatBox;
    @FXML
    VBox rivalChatBox;
    @FXML
    ScrollPane aPane;
    @FXML
    ScrollPane rPane;
    @FXML
    GridPane boardPane;

    private Match match;
    private Profile host;
    private Profile guest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup chatRadio = new ToggleGroup();
        rivalSelect.setToggleGroup(chatRadio);
        audienceSelect.setToggleGroup(chatRadio);
        rivalSelect.setSelected(true);
        this.match = Client.getProfile().getActiveMatch();
        this.host = match.getHostProfile();
        this.hostLabel.setText(host.getUserName());
        this.updateGridPane( this.match.getBoard() );
        if (match.getGuestProfile() != null) {

            //if you are the host and guest has already joined
           /* if (match.getHostProfile().equals(Client.getProfile())) {
                this.guest = match.getGuestProfile();
                this.guestLabel.setText(guest.getUserName());

            }


            //if you are the guest
            else if (Client.getProfile().equals(match.getGuestProfile())) {

                this.guest = match.getGuestProfile();
                this.guestLabel.setText(guest.getUserName());

            }

            //if you are audience and guest has joined already
            else  {*/
            this.guest = match.getGuestProfile();
            this.guestLabel.setText(guest.getUserName());
            // }

        }
       /*
        //if you are the host and quest hasnt joined yet
        else if (match.getHostProfile().equals(Client.getProfile())) {


        }


        //if you are audience and guest hasnt joined yet
        else {

        }*/


        for (Profile audience : match.getAudience()) {

            if (!Client.getProfile().equals(audience))
                audienceBox.getChildren().add(audience.getProfileTile());
            else {
                audienceSelect.setSelected(true);
                rivalSelect.setDisable(true);
                rPane.setVisible(false);
                aPane.setVisible(true);
            }

        }


        Thread waiter = new Thread(waitForPlayers());

        waiter.start();

        Thread chatReciever = new Thread(waitForChat());
        chatReciever.start();


    }

    public void sendMsg() {

        if (rivalSelect.isSelected()) {
            this.sendChatCommand(new SendChatCommand(chatInput.getText(),match,ChatChannelType.RIVAL_CHANNEL,Client.getProfile().getUserName()));
            chatInput.clear();
        }
        else {

            this.sendChatCommand(new SendChatCommand(chatInput.getText(),match,ChatChannelType.AUDIENCES_CHANNEL,Client.getProfile().getUserName()));
            chatInput.clear();
        }


    }

    public void exitRoom() {


    }

    private Runnable waitForChessMoves() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while ( true ) {
                    try {
                        madeAMoveCommand command = (madeAMoveCommand) Client.gameIn.readObject();
                        match.setBoard( command.getBoard() );
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateGridPane( command.getBoard() );
                            }
                        });
                    } catch (IOException|ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        return runnable;

    }

    private Runnable waitForPlayers() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Client.joinGameIn) {

                        try {
                            System.out.println("waiting for a player to join MATCH!");

                            JoinedGameResponse response = (JoinedGameResponse) Client.joinGameIn.readObject();
                            match = response.getMatch();
                            if (response.getJoinerType() == JoinerType.GUEST) {
                                System.out.println(response.getProfile() + " joined as guest");
                                guest = response.getProfile();

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        guestLabel.setText(guest.getUserName());
                                    }
                                });

                            } else {
                                System.out.println(response.getProfile() + " joined as audience");

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        audienceBox.getChildren().add(response.getProfile().getProfileTile());
                                    }
                                });

                            }

                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        };
        return runnable;
    }

    private Runnable waitForChat() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Client.chatIn) {
                        try {
                            System.out.println("Waiting for chat");
                            RecieveChatCommand recieve=(RecieveChatCommand) Client.chatIn.readObject();
                           // HBox box = getChatTile(recieve.getMsg(), recieve.getSender());
                            if (recieve.getChannelType()== ChatChannelType.AUDIENCES_CHANNEL) {

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {


                                        //chatBox.getChildren().addAll( new Label(recieve.getSender()+": "+recieve.getMsg()));
                                        audienceChatBox.getChildren().addAll(new Label(recieve.getSender()+": "+recieve.getMsg()));
                                    }
                                });
                            }
                            else {

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        //System.out.println(box.getChildren());
                                       // chatBox.getChildren().addAll(new Label(recieve.getSender()+": "+recieve.getMsg()));
                                        rivalChatBox.getChildren().addAll(new Label(recieve.getSender()+": "+recieve.getMsg()));
                                    }
                                });



                            }
                        }catch (IOException|ClassNotFoundException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        };
        return runnable;

    }

    private void updateGridPane( Board board ) {
        for ( int i = 0; i < Board.SIZE * Board.SIZE; i++ ) {
            ( (ImageView) boardPane.getChildren().get( i ) ).setImage( new Image( Mohre.MOHRE_IMAGES_ADDRESS + board.getBlocks()[ i / Board.SIZE ][ i % Board.SIZE ].getMohre().toString() ) );
        }
    }


    private HBox getChatTile(String msg, String sender) {
        System.out.println(msg+sender);
        HBox chatTile = new HBox();

        Label senderLabel = new Label(sender + ": ");
        Label msgLabel = new Label(msg);
        HBox.setHgrow(msgLabel, Priority.ALWAYS);
        HBox.setHgrow(senderLabel, Priority.ALWAYS);
        senderLabel.setFont(new Font(15));
        msgLabel.setFont(new Font(15));
        //senderLabel.setStyle("-fx-color-label-visible: red;");
        //msgLabel.setStyle("-fx-color-label-visible: red;");
        chatTile.setStyle("-fx-border-style: solid inside ;" + "-fx-border-width: 2px;"+"-fx-border-color: black;");
        chatTile.setAlignment(Pos.CENTER_LEFT);
       // chatTile.setSpacing(15);

        chatTile.getChildren().addAll(senderLabel, msgLabel);

        return chatTile;
    }

    public void loadRivalChat(){
        rPane.setVisible(true);

        aPane.setVisible(false);
    }
    public void loadAudienceChat(){

        aPane.setVisible(true);

        rPane.setVisible(false);
    }


}
