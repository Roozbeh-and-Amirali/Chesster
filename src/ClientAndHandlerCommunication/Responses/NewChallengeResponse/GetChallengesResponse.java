package ClientAndHandlerCommunication.Responses.NewChallengeResponse;

import ClientAndHandlerCommunication.Responses.Response;
import Game.Match;

import java.util.List;

public class GetChallengesResponse implements Response {
   private List<Match> challenges;

    public List<Match> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Match> challenges) {
        this.challenges = challenges;
    }
}
