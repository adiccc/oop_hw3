package model.tiles.units.players;

public class Warrior extends Player{
    private int abilityCoolDown; // Represents the number of game ticks required to pass before the warrior can cast the ability again
    private int remainingCoolDown; //Represents the number of ticks remained until the warrior can cast its special ability

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
//    @Override
//    public void specialAbility(){
//        remainingCoolDown = abilityCoolDown;
//        health.setCurrent(Math.min(health.getCurrent()+(10*level), health.getCapacity()));
//    }
}
