package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Rokh extends Mohre {

	public Rokh(Color color, Cord cord ) {
		super( color, cord );
	}

	@Override
	public ArrayList<Cord> getValidDests(Board board) {
		int x = this.getCord().getX(), y = this.getCord().getY();
		ArrayList < Cord > returnValue = new ArrayList < Cord > ();
		for ( int xZarib = -1; xZarib <= 1; xZarib++ )
			for ( int yZarib = -1; yZarib <= 1; yZarib++ ) {
				if ( ( xZarib == 0 && yZarib == 0 ) || ( xZarib != 0 && yZarib != 0 ) )
					break;

				for ( int i = 1; x + i * xZarib < Board.SIZE && x + i * xZarib >= 0 &&
						y + i * yZarib < Board.SIZE && y + i * yZarib >= 0; i++ ){
					int currentX = x + i * xZarib, currentY = y + i * yZarib;
					if ( board.getBlocks()[currentY][currentX].getMohre() == null )
						returnValue.add( new Cord( currentX, currentY ) );
					else {
						if (board.getBlocks()[currentY][currentX].getMohre().getColor() != this.getColor())
							returnValue.add(new Cord(currentX, currentY));
						break;
					}
				}

			}
		return returnValue;
	}

}
