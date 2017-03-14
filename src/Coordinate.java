/**
 * Created by Ryan on 1/17/2017.
 *
 * Class that represents a coordinate in the XY plane
 * Used for the Square class
 */
public class Coordinate {
    private int x;
    private int y;

    // constructor
    public Coordinate(int a, int b) {
        x = a;
        y = b;
    }

    // getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        Coordinate temp = (Coordinate) o;
        if (this.getX() != temp.getX() || this.getY() != temp.getX())
            return false;
        return true;
    }
}
