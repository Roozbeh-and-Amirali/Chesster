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

//		In chizHaaE ke paEn neveshtam, baraa-e inan ke bebinim taraf mitoone ghal'e kone yaa na!
		if ( !this.isMoved() ) {	//Age Shaah harekat nadarde bood
			Mohre rokheEhtemaaliChap = board.getBlocks()[y][0].getMohre();	//Rokhi ke shaayad samt-e chap baashe ro zakhire mikonim
//			age mohrehe vojood daasht va rokh bood va harekat nakarde bood
			if ( rokheEhtemaaliChap != null && rokheEhtemaaliChap instanceof Rokh && !rokheEhtemaaliChap.isMoved() )
//			    Age Masiremoon khaali bood
				if ( board.getBlocks()[y][1].getMohre() == null
						&& board.getBlocks()[y][2].getMohre() == null
						&& board.getBlocks()[y][3].getMohre() == null )
					returnValue.add( new Cord( 2, y ) );
			Mohre rokheEhtemaaliRast = board.getBlocks()[y][Board.SIZE-1].getMohre();	//Rokhi ke shaayad samt-e raast baashe ro zakhire mikonim
//			Age mohrehe vojood daasht va rokh bood va harekat nakarde bood
			if ( rokheEhtemaaliRast != null && rokheEhtemaaliRast instanceof Rokh && !rokheEhtemaaliRast.isMoved() )
//				Age masiremoon khaali bood
				if ( board.getBlocks()[y][6].getMohre() == null
						&& board.getBlocks()[y][5].getMohre() == null )
					returnValue.add( new Cord( Board.SIZE - 2, y ) );

		}

		return returnValue;
	}

	@Override
	public String toString() {
		return "shaah";
	}
}
