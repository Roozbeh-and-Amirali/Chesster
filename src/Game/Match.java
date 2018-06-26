package Game;

import Game.ClockNiggas.Clock;
import Game.ClockNiggas.Clockability;
import Game.ClockNiggas.Clocked;
import Game.RateNiggas.Ratability;
import NetworkShit.ServerSide.Handler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
Vaghti ye baaZ misaaZm, ye object az in celass saakhre mishe!
 */

public class Match implements Runnable,Serializable {


	//handler haye user yahe bazi
	private List<Profile> audience = new ArrayList<>();
	//	private HBox matchTile;

	//	In ke baaZ-e mored-e nazar, rated hastesh yaa unrated!
	private Ratability ratability;

//  In ke baaZ-e mored-e nazar, clock dRe yaa na :))
    private Clockability clockability;
//saate bazimoon
	private Clock clock;
	//	Har baaZ do ta player daare, kasi ke baaZ ro sakhte va kasi ke join shode!
	private Profile hostProfile, guestProfile;

	public Match() {
	}

	public Match(Profile hostProfile) {
		this.hostProfile = hostProfile;
	}

	//	Ettelaa'aat-e baaZ ro be soorat-e ye HBox barmigardoone
	public HBox getMatchTile() {
		System.out.println("gettin tile");
		HBox matchTile = new HBox();
		Label hostNameLabel=new Label(this.hostProfile.getUserName());
		Label hostRateLabel=new Label(String.valueOf(this.hostProfile.getRating()));
		Label rateLabel = new Label(ratability.toString());

		Label clockLabel;
		if (clockability instanceof Clocked)
			clockLabel=new Label(clock.toString());
		else
			clockLabel=new Label("Un-Clocked");

		matchTile.getChildren().addAll(hostNameLabel,hostRateLabel,rateLabel,clockLabel);
		matchTile.setSpacing(150);

		matchTile.setAlignment(Pos.CENTER);
		return matchTile;
	}

	@Override
	public void run() {

	}


	public List<Profile> getAudience() {
		return audience;
	}

	public void setAudience(List<Profile> audience) {
		this.audience = audience;
	}

	public Profile getGuestProfile() {
		return guestProfile;
	}

	public Profile getHostProfile() {
		return hostProfile;
	}

	public void setGuestProfile(Profile guestProfile) {
		this.guestProfile = guestProfile;
	}

	public void setHostProfile(Profile hostProfile) {
		this.hostProfile = hostProfile;
	}

	public Ratability getRatability() {
		return ratability;
	}

	public void setRatability(Ratability ratability) {
		this.ratability = ratability;
	}



	public Clockability getClockability() {
		return clockability;
	}

	public void setClockability(Clockability clockability) {
		this.clockability = clockability;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Clock getClock() {
		return clock;
	}
}
