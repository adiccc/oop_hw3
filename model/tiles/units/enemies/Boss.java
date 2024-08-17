package model.tiles.units.enemies;
import model.game.input.InputProvider;
import model.tiles.units.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Boss extends Enemy{
    private int visionRange;
    private int abilityFrequency;
    private int combatTickes;

    public Boss(char tile, String name, int hitPoints, int attack, int defense, int visionRange, int  experienceValue, int abilityFrequency ) {
        super(tile, name, hitPoints, attack, defense, experienceValue);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
        this.combatTickes = 0;
    }

    protected boolean isInRange(Player player){
        return visionRange>=this.position.range(player.getPosition());
    }
    protected boolean isInRangeForAbility(Player player){
        return visionRange>this.position.range(player.getPosition());
    }

    @Override
    public InputProvider turn(Player player) {
        if (isInRangeForAbility(player)) {
            if (combatTickes == abilityFrequency) {
                combatTickes = 0;
                List<Player> playerL = new ArrayList<>();
                playerL.add(player);
                casAbility(playerL);
            } else {
                combatTickes++;
                return chaisePlayer(player);
            }
        }

            combatTickes = 0;
            int direction = generator.generate(5);
            if (direction == 4) {
                direction++;
            }
            return InputProvider.values()[direction];


    }

    private InputProvider chaisePlayer(Player player){
        int dx =position.getX() - player.getPosition().getX();
        int dy =position.getY() - player.getPosition().getY();
        if(Math.abs(dx)>Math.abs(dy)){
            if(dx>0){
                return InputProvider.Left;
            }
            else{
                return InputProvider.Right;
            }
        }
        else{
            if(dy>0){
                return InputProvider.Up;
            }
            else {
                return InputProvider.Down;
            }
        }

    }

    @Override
    public void newTick() {

    }

    @Override
    public <T> void casAbility(List<T> units) {
        Player player = (Player) units.get(0);
        this.battleSpecialAbility(Integer.parseInt(this.getAttack()),player.defend());
        if(!player.alive()){
            player.onDeath();
        }

    }
}
