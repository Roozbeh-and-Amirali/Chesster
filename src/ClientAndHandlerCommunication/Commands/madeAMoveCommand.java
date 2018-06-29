package ClientAndHandlerCommunication.Commands;

import BasicClasses.Cord;
import ChessGame.Board;

public class madeAMoveCommand implements Command {

    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}
