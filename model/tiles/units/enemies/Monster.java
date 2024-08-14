package model.tiles.units.enemies;
import utils.Position;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, hitPoints, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    protected boolean isInRange(Position playerPosition){
        return visionRange<=this.position.range(playerPosition);
    }

    @Override
    public void turn(Position playerPosition) {
//        move(isInRange(playerPosition),playerPosition);
    }

//    private void move(Boolean isInRange, Position playerPosition){
//        if(isInRange){
//            int dx=playerPosition.getX()-this.getPosition().getX();
//            int dy=playerPosition.getY()-this.getPosition().getY();
//            if(Math.abs(dx)>Math.abs(dy)) {
//                if (dx > 0)
//                    this.position.moveLeft();
//                else
//                    this.position.moveRight();
//            }
//            else{
//                if(dy>0)
//                    this.position.moveUp();
//                else
//                    this.position.moveDown();
//            }
//        }
//        else
//    }


    // inbars
//    public void turn(int turnCount) {
//        super.turn(turnCount);
//        List<Player> closePlayer = new ArrayList<>();
//        closePlayer = TargetHandler.candidateTarget(this, this.getPosition(), visionRange);
//        if (closePlayer.size() > 0) {
//            int dx = this.getPosition().x - closePlayer.get(0).getPosition().x;
//            int dy = this.getPosition().y - closePlayer.get(0).getPosition().y;
//            if (Math.abs(dx) > Math.abs(dy)) {
//                if (dx > 0)
//                    this.move(InputProvider.Left);
//                else
//                    this.move(InputProvider.Right);
//            } else {
//                if (dy > 0)
//                    this.move(InputProvider.Up);
//                else
//                    this.move(InputProvider.Down);
//            }
//        } else {
//            this.move(rndArrs[(new Random().nextInt(5))]);
//        }
//    }
}
