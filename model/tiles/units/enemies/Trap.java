package model.tiles.units.enemies;

import model.game.input.InputProvider;
import model.tiles.units.players.Player;

import java.util.List;

public class Trap extends Enemy {
    private int visibiltyTime;
    private int invisibiltyTime;
    private boolean isVisible = false;
    private int tickCount = 0;
    private final char VISIABLE_TILE;
    private char UNVISIABLE_TILE='.';

    public Trap(char tile, String name, int hitPoints, int attack, int defense, int experinceValue, int visibiltyTime, int invisibiltyTime) {
        super(tile,name,hitPoints,attack,defense,experinceValue);
        this.VISIABLE_TILE = tile;
        this.visibiltyTime = visibiltyTime;
        this.invisibiltyTime = invisibiltyTime;
    }

    protected boolean isInRange(Player player){
        return 2>=this.position.range(player.getPosition());
    }

    @Override
    public InputProvider turn(Player player){
        updateVisibilty();
        if(isInRange(player))
            accept(player);
        return InputProvider.Wait;
    }

    private void updateVisibilty(){
        isVisible=tickCount<visibiltyTime;
        if(isVisible)
            this.setTile(VISIABLE_TILE);
        else
            this.setTile(UNVISIABLE_TILE);
        if(tickCount==(visibiltyTime+invisibiltyTime))
            tickCount=0;
        else
            tickCount++;
    }

    @Override
    public void newTick() {

    }

    @Override
    public <T> void casAbility(List<T> units) {

    }
}
