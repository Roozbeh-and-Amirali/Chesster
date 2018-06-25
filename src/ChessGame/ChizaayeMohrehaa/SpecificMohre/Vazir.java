package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

public class Vazir extends Mohre {

	public Vazir(Color color, Cord cord ) {
		super( color, cord );
	}

	@Override
	public boolean isMoveValid(Board board, Cord dest) {
		return false;
	}

}
