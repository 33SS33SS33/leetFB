package test;

/**
 * Created by shanshan on 3/11/18.
 */
public class Location {
    private int row;
    private int col;

    public Location(int r, int c) {
        row = r;
        col = c;
    }

    public boolean isSameAs(int r, int c) {
        return row == r && col == c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
