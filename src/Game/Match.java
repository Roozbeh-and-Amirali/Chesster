package Game;

import Game.ClockNiggas.Clockability;
import Game.RateNiggas.Ratability;
import NetworkShit.ServerSide.Handler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/*
Vaghti ye baaZ misaaZm, ye object az in celass saakhre mishe!
 */

public class Match implements Runnable {


	//handler haye user yahe bazi
	private List<Handler> audience = new ArrayList<>();
	//	private HBox matchTile;

	//	In ke baaZ-e mored-e nazar, rated hastesh yaa unrated!
	private Ratability ratability;

//  In ke baaZ-e mored-e nazar, clock dRe yaa na :))
    private Clockability clockability;

	//	Har baaZ do ta player daare, kasi ke baaZ ro sakhte va kasi ke join shode!
	private Handler hostHandler, guestHandler;

	public Match() {
	}

	public Match(Handler hostHandler) {
		this.hostHandler = hostHandler;
	}

	//	Ettelaa'aat-e baaZ ro be soorat-e ye HBox barmigardoone
	public HBox getMatchTile() {
		HBox matchTile = new HBox();
		Label rateLabel = new Label(ratability.toString());
		matchTile.getChildren().addAll(rateLabel);
		return matchTile;
	}

	@Override
	public void run() {

	}


	public List<Handler> getAudience() {
		return this.audience;
	}

	public void setAudience(List<Handler> audience) {
		this.audience = audience;
	}

	public Ratability getRatability() {
		return ratability;
	}

	public void setRatability(Ratability ratability) {
		this.ratability = ratability;
	}

	public Handler getHostHandler() {
		return hostHandler;
	}

	public void setHostHandler(Handler hostHandler) {
		this.hostHandler = hostHandler;
	}

	public Handler getGuestHandler() {
		return guestHandler;
	}

	public void setGuestHandler(Handler guestHandler) {
		this.guestHandler = guestHandler;
	}

	public Clockability getClockability() {
		return clockability;
	}

	public void setClockability(Clockability clockability) {
		this.clockability = clockability;
	}

}
