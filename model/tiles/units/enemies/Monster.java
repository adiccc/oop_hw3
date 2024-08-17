package model.tiles.units.enemies;
import model.game.input.InputProvider;
import model.tiles.units.players.Player;
import utils.Position;

import java.util.List;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int hitPoints, int attack, int defense, int visionRange, int  experienceValue) {
        super(tile, name, hitPoints, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    protected boolean isInRange(Player player){
        return visionRange>=this.position.range(player.getPosition());
    }

    @Override
    public InputProvider turn(Player player) {
        if(isInRange(player)){
            return chaisePlayer(player);
        }
        else {
           int direction =generator.generate(5);
           if(direction==4){direction++;}
           return InputProvider.values()[direction];
        }
    }

    private InputProvider chaisePlayer(Player player){
        int x =position.getX();
        int y =position.getY();
        if(player.getPosition().getX()>x)
            return InputProvider.Right;
        else if(player.getPosition().getX()<x)
            return InputProvider.Left;
        else if(player.getPosition().getY()>y)
            return InputProvider.Up;
        else
            return InputProvider.Down;
    }

    @Override
    public void newTick() {

    }

    @Override
    public <T> void casAbility(List<T> units) {

    }
}
