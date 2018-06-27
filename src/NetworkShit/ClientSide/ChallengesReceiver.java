package NetworkShit.ClientSide;

import ClientAndHandlerCommunication.Commands.NewChallengeCommands.GetChallengesCommand;
import ClientAndHandlerCommunication.Responses.NewChallengeResponse.GetChallengesResponse;
import ClientAndHandlerCommunication.Responses.Response;
import Game.Match;
import UIUX.Controllers.ChallengesPageController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ChallengesReceiver extends Thread {
    private List<Match> challenges;
    private VBox challengesVBox;

    public ChallengesReceiver(List<Match> challenges,VBox challengesVBox){
        this.challenges=challenges;
        this.challengesVBox=challengesVBox;
    }


    @Override
    public void run() {


    }




    public List<Match> getChallenges() {
        return challenges;
    }
}
