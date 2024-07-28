package utils;

public class Position {
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

    // update the moves, this will not work well
//    public void moveUp(){
//        this.y--;
//    }
//    public void moveDown(){
//        this.y++;
//    }
//    public void moveLeft(){
//        this.x--;
//    }
//    public void moveRight(){
//        this.x++;
//    }
//    public void moveRandom(){
//        int rndMove = (int)(Math.random()*5);
//        switch(rndMove){
//            case 0 :
//                break;
//            case 1:
//                moveDown();
//                break;
//            case 2:
//                moveUp();
//                break;
//            case 3:
//                moveLeft();
//                break;
//            case 4:
//                moveRight();
//                break;
//        }
//    }
}
