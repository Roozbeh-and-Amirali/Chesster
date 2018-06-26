
package ChessGame.ChizaayeMohrehaa;

import BasicClasses.Cord;
import ChessGame.Board;
import Enums.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Mohre {

	private static String MOHRE_IMAGES_ADDRESS = "/Assets/Mohre/";

//	private static Map< String, ImageView>;

	private Color color;
	private Cord cord;
	private boolean isMoved;

	public void move( Board board, Cord dest ){
		this.setMoved( true );
		board.getBlocks()[dest.getY()][dest.getX()].setMohre( this );
		board.getBlocks()[this.getCord().getY()][this.getCord().getX()].setMohre( null );
		this.setCord( dest );
	}

	public boolean isMoveValid( Board board, Cord dest ){
		return ( this.getValidDests( board ).contains( dest ) );
	}

	public void addCorToValidCors( Board board, ArrayList< Cord > cords, Cord theCord ){ //Cor manfi nadaashte baashe... va mohre-e khoD oonjaa nabaashe!
		if ( theCord.getX() >= 0 && theCord.getX() < Board.SIZE && theCord.getY() >= 0 && theCord.getY() < Board.SIZE )
			if ( board.getBlocks()[theCord.getY()][theCord.getX()].getMohre().getColor() != this.getColor() )
				cords.add( theCord );
	}

	public abstract ArrayList< Cord > getValidDests( Board board );



	public ImageView getTile() {
		return new ImageView( new Image( Mohre.MOHRE_IMAGES_ADDRESS + this.toString() ) );
	}

	public Mohre(Color color, Cord cord) {
		this.setColor( color );
		this.setCord( cord );
		this.setMoved( false );
	}

	public Mohre() {

	}

	@Override
	public String toString() {
		return this.toString() + this.getColor().toString();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Cord getCord() {
		return cord;
	}

	public void setCord(Cord cord) {
		this.cord = cord;
	}

	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean moved) {
		isMoved = moved;
	}

}
