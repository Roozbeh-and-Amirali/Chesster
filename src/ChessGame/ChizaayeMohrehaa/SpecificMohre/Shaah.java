package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Shaah extends Mohre {

	public Shaah(Color color, Cord cord ) {
		super( color, cord );
	}

    @Override
    public ArrayList<Cord> getValidDests(Board board) {
	    ArrayList < Cord > returnValue = new ArrayList < Cord > ();
	    int x = this.getCord().getX();
	    int y = this.getCord().getY();
	    this.addCorToValidCors( board, returnValue, new Cord( x - 1, y - 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x, y - 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x + 1, y - 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x + 1, y ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x + 1, y + 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x, y + 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x - 1, y + 1 ) );
	    this.addCorToValidCors( board, returnValue, new Cord( x - 1, y ) );
	    return returnValue;
    }

    @Override
    public String toString() {
        return "shaah";
    }
}
