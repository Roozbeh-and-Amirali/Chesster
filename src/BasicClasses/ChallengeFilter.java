package BasicClasses;

import Game.ClockNiggas.Clock;
import Game.Match;

import java.io.Serializable;

public class ChallengeFilter implements Serializable {
    private Rating minRating;
    private Rating maxRating;

    private Clock maxClock;
    private Clock minClock;

    ChallengeFilter(Rating minRating,Rating maxRating,Clock minClock,Clock maxClock){
        this.minClock=minClock;
        this.maxClock=maxClock;
        this.minRating=minRating;
        this.maxRating=maxRating;




    }


    public boolean isChallengeOk (Match match){
        return  (match.getHostProfile().getRating()<this.maxRating.getValue())&&(match.getHostProfile().getRating()>this.minRating.getValue())&&(match.getClock().getTime().getSeconds()<this.maxClock.getTime().getSeconds())&&(match.getClock().getTime().getSeconds()>this.minClock.getTime().getSeconds());
    }

}
