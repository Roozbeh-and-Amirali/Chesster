package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Asb extends Mohre {

	public Asb(Color color, Cord cord ) {
		super( color, cord );
	}

	@Override
	public ArrayList<Cord> getValidDests(Board board) {
		int x = this.getCord().getX(), y = this.getCord().getY();
		ArrayList < Cord > returnValue = new ArrayList < Cord > ();
	    for ( int i = -2; i <= 2; i++ )
	    	for ( int j = -2; j <= 2; j++ ) {
	    		if ( Math.abs( i ) == Math.abs( j ) || i == 0 || j == 0 )
	    			continue;
	    		this.addCorToValidCors( board, returnValue, new Cord( x + i, y + j ) );
			}
		return returnValue;
	}



	@Override
	public String toString() {
		return "asb";
	}

}
