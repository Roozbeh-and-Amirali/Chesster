package ClientAndHandlerCommunication.Commands;

import Game.Match;

public class JoinGameCommand implements Command {
     private Match match;

     public JoinGameCommand(Match match){
         this.match=match;
     }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
