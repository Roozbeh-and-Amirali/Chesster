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

		ArrayList < Cord > returnValue = new ArrayList < Cord > ();
		int x = this.getCord().getX(), y = this.getCord().getY();
		int zarib = ( this.getColor() == Color.WHITE ? -1 : 1 );	//Ye jooraaE dREm jahat-e jolo ro baraa-e sefid o siaah moshakhas mikonim
		if ( board.getBlocks()[ y + 1 * zarib ][ x ].getMohre() == null ) {
			returnValue.add( new Cord( x , y + 1 * zarib ) );
			if ( !this.isMoved() && board.getBlocks()[ y + 2 * zarib ][ x ].getMohre() == null )
				returnValue.add( new Cord( x, y + 2 * zarib ) );
		}
		if ( x > 0 && board.getBlocks()[ y + 1 * zarib ][ x - 1 ].getMohre().getColor() != this.getColor() )
			returnValue.add( new Cord( x - 1, y + 1 * zarib ) );
		if ( x < Board.SIZE - 1 && board.getBlocks()[ y + 1 * zarib ][ x + 1 ].getMohre().getColor() != this.getColor() )
			returnValue.add( new Cord( x + 1, y + 1 * zarib ) );

		return returnValue;
	}

	@Override
	public String toString() {
		return "sarbaaz";
	}
}
