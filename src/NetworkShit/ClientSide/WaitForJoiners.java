package NetworkShit.ClientSide;

import ClientAndHandlerCommunication.Commands.NewChallengeCommands.DeleteChallengesCommand;
import ClientAndHandlerCommunication.Responses.JoinedGameResponse;
import Game.Match;
import UIUX.Controllers.ParentController;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaitForJoiners extends ParentController implements Runnable {
    private List<Match> matches = new ArrayList<>();

    @Override
    public void run() {

        synchronized (Client.joinGameIn) {

                    try {
                        System.out.println("running");
                        JoinedGameResponse response = (JoinedGameResponse) Client.joinGameIn.readObject();
                        Client.getProfile().setActiveMatch(response.getMatch());
                        this.sendUserCommand(new DeleteChallengesCommand(new ArrayList<>(Client.getProfile().getRequestedMatches())));
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                        loadPage("GameRoomPage");
                            }
                        });
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }




        }

    }

    public List<Match> getMatches() {
        return matches;
    }
}
