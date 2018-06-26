package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

import java.util.ArrayList;

public class Fil extends Mohre {

	public Fil(Color color, Cord cord ) {
		super( color, cord );
	}

	@Override
	public ArrayList<Cord> getValidDests(Board board) {
		ArrayList < Cord > returnValue = new ArrayList < Cord > ();
		for ( int yZarib = -1; yZarib <= 1; yZarib += 2 ) {
			for (int xZarib = -1; xZarib <= 1; xZarib += 2) {
				for (int i = 1; i * xZarib + this.getCord().getX() < Board.SIZE && i * xZarib + this.getCord().getX() >= 0
						&& i * yZarib + this.getCord().getY() < Board.SIZE && i * yZarib + this.getCord().getY() >= 0; i++) {

					int currentX = this.getCord().getX() + i * xZarib, currentY = this.getCord().getY() + i * yZarib;
					if (board.getBlocks()[currentY][currentX].getMohre() == null)
						returnValue.add(new Cord(currentX, currentY));
					else {
						if (board.getBlocks()[currentY][currentX].getMohre().getColor() != this.getColor())
							returnValue.add(new Cord(currentX, currentY));
						break;
					}

				}
			}
		}
		return returnValue;
	}

	@Override
	public String toString() {
		return "fil";
	}
}
