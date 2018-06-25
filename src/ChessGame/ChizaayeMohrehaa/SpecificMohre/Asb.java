package ChessGame.ChizaayeMohrehaa.SpecificMohre;

import BasicClasses.Cord;
import ChessGame.Board;
import ChessGame.ChizaayeMohrehaa.Mohre;
import Enums.Color;

public class Asb extends Mohre {

    public Asb(Color color, Cord cord ) {
        super( color, cord );
    }

    @Override
    public boolean isMoveValid(Board board, Cord dest) {
        return false;
    }

}
