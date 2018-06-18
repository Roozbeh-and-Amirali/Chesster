package Game;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/*
Vaghti ye baaZ misaaZm, ye object az in celass saakhre mishe!
 */

public class Match {

//	private HBox matchTile;

//	In ke baaZ-e mored-e nazar, rated hastesh yaa unrated!
	private Ratability ratability;

//	Har baaZ do ta player daare, kasi ke baaZ ro sakhte va kasi ke join shode!
	private Profile hostProfile, guestProfile;

	Match() {

	}

	Match(Profile hostProfile){
		this.hostProfile=hostProfile;
	}

//	Ettelaa'aat-e baaZ ro be soorat-e ye HBox barmigardoone
	public HBox getMatchTile() {

		HBox matchTile = new HBox();

		Label rateLabel = new Label( ratability.toString() );

		matchTile.getChildren().addAll(rateLabel);
		return matchTile;
	}

}
