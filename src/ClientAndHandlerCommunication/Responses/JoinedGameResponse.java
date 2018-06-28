package ClientAndHandlerCommunication.Responses;

import Game.Match;
import Game.Profile;

public class JoinedGameResponse implements Response {
    private Match match;

    public JoinedGameResponse(Match match)
    {
        this.match=match;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
