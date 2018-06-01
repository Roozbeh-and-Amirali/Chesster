package Game;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Match {

//	private HBox matchTile;
	private Ratability ratability;
	private Profile hostProfile, guestProfile;

	Match() {

	}

	public HBox getMatchTile() {

		HBox matchTile = new HBox();

		Label rateLabel = new Label();
		rateLabel.setText(ratability.toString());

		matchTile.getChildren().addAll(rateLabel);
		return matchTile;
	}

}
