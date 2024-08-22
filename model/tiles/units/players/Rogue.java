package model.tiles.units.players;

import model.tiles.units.Unit;
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
        super.levelUp();
        currentEnergy = 100;
        attack = attack+(level*3);

    }
    @Override
    public void newTick(){
       currentEnergy = Math.min(currentEnergy+10, 100);
    }


    public void specialAbility(List<Enemy> enemies) {
        if(currentEnergy <  cost) {
            messageCallback.send(String.format("%s tried to cast %s , but there is not enough energy",getName(),ABILITY_NAME));
        }
        else{
            List<Enemy> enemiesInRange = enemies.stream()
                    .filter(e -> e.getPosition().range(this.position) <=2)
                    .toList();
            if(enemiesInRange.isEmpty()) {
                messageCallback.send(String.format("%s tried to cast %s , but there is no any enemy in range",getName(),ABILITY_NAME));
            }
            else{
                messageCallback.send(String.format("%s cast %s",getName(),ABILITY_NAME));
                currentEnergy = currentEnergy - cost;
                for (Enemy e : enemiesInRange){
                    int defense = e.defend();
                    messageCallback.send(String.format("%s rolled %d points",e.getName(),defense));
                    int damage = e.battleSpecialAbility(Integer.parseInt(this.getAttack()), defense);
                    messageCallback.send(String.format("%s hit %s for %d ability damage ",this.getName() , e.getName() , damage ));
                    if(!e.alive())
                        killEnemy(e);
                }
            }

        }


    }

    public String description() {
        return String.format("%s \t\tEnergy: %d /100",super.description(),getCurrentEnergy());
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

}
