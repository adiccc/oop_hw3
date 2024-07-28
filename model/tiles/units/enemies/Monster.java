package model.tiles.units.enemies;
import utils.Position;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, int visionRange) {
        super(tile, name, hitPoints, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    private boolean isInRange(Position playerPosition){
        return visionRange<=this.position.range(playerPosition);
    }

    @Override
    public void turn(Position playerPosition){
        if(isInRange(playerPosition)){
            int dx=playerPosition.getX()-this.getPosition().getX();
            int dy=playerPosition.getY()-this.getPosition().getY();
            if(Math.abs(dx)>Math.abs(dy)) {
                if (dx > 0)
                    this.position.moveLeft();
                else
                    this.position.moveRight();
            }
            else{
                if(dy>0)
                    this.position.moveUp();
                else
                    this.position.moveDown();
            }
        }
        else
        {
            this.position.moveRandom();
        }
    }
}
