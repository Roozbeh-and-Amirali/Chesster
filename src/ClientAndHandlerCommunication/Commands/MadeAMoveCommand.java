package ClientAndHandlerCommunication.Commands;

import Game.Match;

public class MadeAMoveCommand implements Command {

    private Match match;

    public MadeAMoveCommand(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

}
