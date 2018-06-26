package BasicClasses;

public class Cord {

    private int x, y;

    public Cord(){
    }

    public Cord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof Cord )
            return ( this.x == ((Cord) obj).getX() && this.y == ((Cord) obj).getY() );
        else return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
