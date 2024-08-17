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
        return super.description() + "\t\tCooldown: " + remainingCoolDown + "/" + abilityCoolDown;
    }


    @Override
    public void newTick(){
        remainingCoolDown--;
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void specialAbility(List<Enemy> enemies) {
        if (remainingCoolDown <= 0) {
            int healthGain=-health.getCurrent()+health.updateCurrentHealthOnCast(defense);
            messageCallback.send(getName()+" used "+ABILITY_NAME+", healing for "+healthGain);
            for (Enemy e : enemies) {
                if (e.getPosition().range(this.position) <= 3) {
                    remainingCoolDown = abilityCoolDown;
                    int damageTaken=e.battleSpecialAbility((int) (health.getCapacity() * 0.1), e.defend());
                    messageCallback.send(getName()+" dealt "+damageTaken+" damage to "+e.getName());
                    if (!e.alive()) {
                        addExperience(e.experienceValue());
                        e.onDeath();
                    }
                    return;
                }
            }
        }
        else{
            messageCallback.send(getName()+" need to coolDown before using "+ABILITY_NAME);}
    }

    public int getRemainingCoolDown() {
        return remainingCoolDown;
    }


    @Override
    public <T> void casAbility(List<T> units) {
        specialAbility((List<Enemy>) units);
    }
}
