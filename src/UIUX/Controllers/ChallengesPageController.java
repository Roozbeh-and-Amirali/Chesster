package UIUX.Controllers;

import ClientAndHandlerCommunication.Commands.NewChallengeCommands.GetChallengesCommand;
import ClientAndHandlerCommunication.Responses.NewChallengeResponse.GetChallengesResponse;
import Game.Match;
import NetworkShit.ServerSide.Server;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChallengesPageController extends ParentController implements Initializable {

    @FXML
    VBox challengesVBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Match> challenges;

        GetChallengesResponse response=(GetChallengesResponse)this.sendCommand(new GetChallengesCommand());

        challenges=response.getChallenges();



        // vaghti safhe baz mishe... challengaye tooye server roo miad tooye vboxesh add mikone
        this.createHbox(challenges);

    }

    public void createNewChallenge(){
        this.loadPage("NewChallengePage");

    }

    public void backToMenu(){
        this.loadPage("MainMenu");
    }

    private void createHbox(List<Match> challenges){

        for (Match match: challenges) {
            HBox matchBox=match.getMatchTile();
            matchBox.setStyle("-fx-border-style: solid inside ;" + "-fx-border-width: 2px;"+"-fx-border-color: black;");
            matchBox.setOnMouseEntered((event1)-> {
                matchBox.setEffect(new DropShadow());
            });
            matchBox.setOnMouseExited((event2)-> {
                matchBox.setEffect(null);
            });
            this.challengesVBox.getChildren().add(matchBox);
        }
    }


}
