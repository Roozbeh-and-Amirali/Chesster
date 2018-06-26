package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Vazir extends Mohre {

	public Vazir(Color color, Cord cord ) {
		super( color, cord );
	}

    @Override
    public ArrayList<Cord> getValidDests(Board board) {
        ArrayList < Cord > returnValue = new ArrayList < Cord > ();
        Fil fil = new Fil( this.getColor(), this.getCord() );
        returnValue.addAll( fil.getValidDests( board ) );
        Rokh rokh = new Rokh( this.getColor(), this.getCord() );
        returnValue.addAll( rokh.getValidDests( board ) );
        return returnValue;
    }

    @Override
    public String toString() {
        return "vazir";
    }

}
