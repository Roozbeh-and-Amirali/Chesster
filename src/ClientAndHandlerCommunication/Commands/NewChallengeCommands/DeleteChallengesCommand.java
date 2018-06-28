package ClientAndHandlerCommunication.Commands.NewChallengeCommands;

import ClientAndHandlerCommunication.Commands.Command;
import Game.Match;

import java.util.List;

public class DeleteChallengesCommand implements Command {
    private List<Match> list;

    public DeleteChallengesCommand(List<Match> list){
        this.list=list;

    }

    public List<Match> getList() {
        return list;
    }
}
