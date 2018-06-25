
package ChessGame.ChizaayeMohrehaa;

import BasicClasses.Cord;
import ChessGame.Board;

public abstract class Mohre {

    public abstract void move( Board board, Cord dest );
    public abstract boolean isMoveValid( Board board, Cord dest );

}
