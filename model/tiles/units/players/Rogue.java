package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;

public class Rogue extends Player {
    private int cost;
    private int currentEnergy;
    private final String ABILITY_NAME="Fan Of Knives";

    public Rogue(String name, int hitPoints, int attack, int defense, int cost) {
        super(name, hitPoints, attack, defense);
        this.cost = cost;
        this.currentEnergy = 100;

    }
    @Override
    public void levelUp(){
        currentEnergy = 100;
        attack = attack+(level*3);

    }
    @Override
    public void newTick(){
       currentEnergy = Math.min(currentEnergy+10, 100);
    }

    @Override
    public void visit(Player p) {

    }


    public void specialAbility(List<Enemy> enemies) {
        if(currentEnergy <  cost) {
            messageCallback.send(getName()+" tried to cast "+ABILITY_NAME+", but there is not enough energy"  );
        }
        else{
            List<Enemy> enemiesInRange = enemies.stream()
                    .filter(e -> e.getPosition().range(this.position) <=2)
                    .toList();
            if(enemiesInRange.isEmpty()) {
                messageCallback.send(getName()+" tried to cast "+ABILITY_NAME+", but there is no any enemy in range"  );
            }
            else{
                messageCallback.send(getName()+ " cast " + ABILITY_NAME);
                currentEnergy = currentEnergy - cost;
                for (Enemy e : enemiesInRange){
                    int defense = e.defend();
                    messageCallback.send(e.getName() + "  rolled " + defense + " points ");
                    int damage = e.battleSpecialAbility(Integer.parseInt(this.getAttack()), defense);
                    messageCallback.send(this.getName() + " hit " + e.getName() + " for " + damage + " ability damage ");
                    if(!e.alive()){
                        addExperience(e.experienceValue());
                        e.onDeath();
                    }
                }
            }

        }


    }

    public String description() {
        return super.description() +
                "\t\tEnergy: " + getCurrentEnergy() + "/100";
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public <T> void casAbility(List<T> units) {
        specialAbility((List<Enemy>) units);
    }
}
