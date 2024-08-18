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
        if (getY() > position.getY())
            return 1;
        if (getY() < position.getY())
            return -1;
        if (getX() > position.getX())
            return 1;
        if (getX() < position.getX())
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Position){
            return compareTo((Position)o)==0;
        }
       return false;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    // return the range between two objects
    public boolean isInRange(Position c2, int range) {
        return range >= this.range(c2);
    }
}
