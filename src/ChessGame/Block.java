package ChessGame;

import ChessGame.ChizaayeMohrehaa.Mohre;

public class Block {

    Mohre mohre;

    public Block( Mohre mohre ) {
        this.setMohre( mohre );
    }

    public Mohre getMohre() {
        return mohre;
    }

    public void setMohre(Mohre mohre) {
        this.mohre = mohre;
    }

}
