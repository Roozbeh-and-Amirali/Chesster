package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Sarbaaz extends Mohre{

	public Sarbaaz( Color color, Cord cord ) {
		super( color, cord );
	}

	@Override
	public ArrayList<Cord> getValidDests(Board board) {
	}

	@Override
	public String toString() {
		return "sarbaaz";
	}
}
