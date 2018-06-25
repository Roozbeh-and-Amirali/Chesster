package ChessGame;

import BasicClasses.Cord;
import ChessGame.ChizaayeMohrehaa.Mohre;
import ChessGame.ChizaayeMohrehaa.SpecificMohre.*;
import Enums.Color;

public class Board {

	private static final int SIZE = 8;

	Block[][] blocks;

	public Board() {
		this.blocks = new Block[ Board.SIZE ][ Board.SIZE ];
		this.initializeBlocks();
	}

	private void initializeBlocks() {
        this.putFirstRow( 0, Color.BLACK );
        this.putSarbaazhaa( 1, Color.BLACK );
        this.putFirstRow( Board.SIZE - 1, Color.WHITE );
        this.putSarbaazhaa( Board.SIZE - 1, Color.WHITE );
	}

	private void putFirstRow( int satr, Color color ) {
	    this.blocks[satr][0] = new Block( new Rokh( color, new Cord( satr, 0 ) ) );
	    this.blocks[satr][1] = new Block( new Asb( color, new Cord( satr, 1 ) ) );
	    this.blocks[satr][2] = new Block( new Fil( color, new Cord( satr, 2 ) ) );
	    this.blocks[satr][3] = new Block( new Shaah( color, new Cord( satr, 3 ) ) );
	    this.blocks[satr][4] = new Block( new Vazir( color, new Cord( satr, 4 ) ) );
	    this.blocks[satr][5] = new Block( new Fil( color, new Cord( satr, 5 ) ) );
	    this.blocks[satr][6] = new Block( new Asb( color, new Cord( satr, 6 ) ) );
	    this.blocks[satr][7] = new Block( new Rokh( color, new Cord( satr, 7 ) ) );
    }

    private void putSarbaazhaa( int satr, Color color ) {
	    for ( int i = 0; i < Board.SIZE; i++ )
	        this.blocks[satr][i] = new Block( new Sarbaaz( color, new Cord( satr, i ) ) );
    }

}
