package UIUX.Controllers;

import ClientAndHandlerCommunication.Responses.JoinedGameResponse;
import Enums.JoinerType;
import Game.Match;
import Game.Profile;
import NetworkShit.ClientSide.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameRoomController implements Initializable {
    @FXML
    TextField chatBox;
    @FXML
    Label hostLabel;
    @FXML
    Label guestLabel;
    @FXML
    VBox audienceBox;

    private Match match;
    private Profile host;
    private Profile guest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.match = Client.getProfile().getActiveMatch();
        this.host = match.getHostProfile();
        this.hostLabel.setText(host.getUserName());
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

        }
        Thread waiter = new Thread(waitForPlayers());

        waiter.start();


    }

    public void sendMsg() {


    }

    public void exitRoom() {


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


}
