package model.tiles.units.enemies;

import utils.Position;

public class Trap extends Enemy {
    private int visibiltyTime;
    private int invisibiltyTime;
    private boolean isVisible = false;
    private int tickCount = 0;

    public Trap(char tile, String name, int hitPoints, int attack, int defense, int experinceValue, int visibiltyTime, int invisibiltyTime) {
        super(tile,name,hitPoints,attack,defense,experinceValue);
        this.visibiltyTime = visibiltyTime;
        this.invisibiltyTime = invisibiltyTime;
    }

    protected boolean isInRange(Position playerPosition){
        return 2>=this.position.range(playerPosition);
    }

    @Override
    public void turn(Position playerPosition){
        updateVisibilty();
        if(isInRange(playerPosition)){
//            wtf
//            attack(playerPosition);
        }
    }

    private void updateVisibilty(){
        isVisible=tickCount<visibiltyTime;
        if(tickCount==(visibiltyTime+invisibiltyTime))
            tickCount=0;
        else
            tickCount++;
    }
}
