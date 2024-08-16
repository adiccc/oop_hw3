package model.tiles.units.enemies;

import model.tiles.units.players.Player;
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

    protected boolean isInRange(Player player){
        return 2>=this.position.range(player.getPosition());
    }

    @Override
    public void turn(Player player){
        updateVisibilty();
        if(isInRange(player))
            accept(player);
    }

    private void updateVisibilty(){
        isVisible=tickCount<visibiltyTime;
        if(tickCount==(visibiltyTime+invisibiltyTime))
            tickCount=0;
        else
            tickCount++;
    }

    @Override
    public void newTick() {

    }
}
