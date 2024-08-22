package model.tiles.units.players;

import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;

import java.util.List;

public class Warrior extends Player{
    private int abilityCoolDown; // Represents the number of game ticks required to pass before the warrior can cast the ability again
    private int remainingCoolDown;//Represents the number of ticks remained until the warrior can cast its special ability
    private final String ABILITY_NAME="Avenger's Shield";

    public Warrior(String name, int hitPoints, int attack, int defense, int abilityCoolDown ) {
        super(name, hitPoints, attack, defense);
        this.abilityCoolDown = abilityCoolDown;
        this.remainingCoolDown = 0;
    }
    public Warrior(String name, int attack, int defense, int abilityCoolDown ) {
        this(name,0,attack,defense,abilityCoolDown);
    }
    @Override
    public void levelUp(){
        super.levelUp();
        remainingCoolDown = 0;
        health.increaseMax(5*level);
        attack = attack+(2*level);
        defense = defense+level;
    }
    // the Warrior describe
    @Override
    public String description() {
        return String.format("%s \t\tCooldown: %d/%d",super.description(), remainingCoolDown, abilityCoolDown);
    }

    @Override
    public void newTick(){
        remainingCoolDown--;
    }

    @Override
    public void specialAbility(List<Enemy> enemies) {
        if (remainingCoolDown <= 0) {
            int healthGain=-health.getCurrent()+health.updateCurrentHealthOnCast(defense);
            messageCallback.send(String.format("%s used %s, healing for %d",getName(),ABILITY_NAME,healthGain));
             for (Enemy e : enemies) {
                if (e.getPosition().range(this.position) <= 3) {
                    remainingCoolDown = abilityCoolDown;
                    int damageTaken=e.battleSpecialAbility(Double.valueOf(health.getCapacity() * 0.1).intValue(), e.defend());
                    messageCallback.send(String.format("%s dealt %d damage to %s",getName(),damageTaken,e.getName()));
                    if (!e.alive())
                        killEnemy(e);
                    return;
                }
            }
        }
        else{
            messageCallback.send(String.format("%s tried to cast %s , but there is a cooldown: %d",getName(),ABILITY_NAME,remainingCoolDown));}
    }

    public int getRemainingCoolDown() {
        return remainingCoolDown;
    }
}
