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

    private void tryCombat(Player player){
        if(this.position.range(player.getPosition())<=1)
            battle(player);
    }

    @Override
    public void turn(Player player) {
        Position newp=this.getPosition();
        if(isInRange(player)){
            tryCombat(player);
            newp = chaisePlayer(player);
        }
        else {
           int direction =generator.generate(5);
        }
    }

    private Position chaisePlayer(Player player){
        int direction=0;
        int x =position.getX();
        int y =position.getY();
        if(player.getPosition().getX()>x)
            x++;
        else if(player.getPosition().getX()<x)
            x--;
        else if(player.getPosition().getY()>y)
            y++;
        else
            y--;
        return new Position(x, y);
    }

    @Override
    public void newTick() {

    }
}
