package model.tiles.units.enemies;
import model.tiles.units.players.Player;
import utils.Position;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, hitPoints, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    protected boolean isInRange(Player player){
        return visionRange<=this.position.range(player.getPosition());
    }

    @Override
    public void turn(Player player) {
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

}
