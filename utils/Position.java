package utils;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double range(Position other){
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int compareTo(Position position) {
        if (getX() > position.getX())
            return 1;
        if (getX() < position.getX())
            return -1;
        if (getY() > position.getY())
            return 1;
        if (getY() < position.getY())
            return -1;
        return 0;
    }

    // return the range between two objects
    public boolean isInRange(Position c2, int range) {
        return range >= this.range(c2);
    }
}
