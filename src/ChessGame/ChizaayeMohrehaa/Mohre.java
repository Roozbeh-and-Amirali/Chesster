
package ChessGame.ChizaayeMohrehaa;

import BasicClasses.Cord;
import ChessGame.Board;
import Enums.Color;

public abstract class Mohre {

	private Color color;
	private Cord cord;

	public void move( Board board, Cord dest ){
	}

	public abstract boolean isMoveValid( Board board, Cord dest );

	public Mohre(Color color, Cord cord) {
		this.setColor( color );
		this.setCord( cord );
	}

	public Mohre() {

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

}
