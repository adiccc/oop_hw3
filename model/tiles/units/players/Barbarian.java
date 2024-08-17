package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;

public class Barbarian extends Player{
    private int currEnergy;
    private int energyPool;
    private int costOfSpecialAbility;
    public Barbarian(String name, int hitPoints, int attack, int defense, int energy, int energyPool) {
        super(name, hitPoints, attack, defense);
        this.currEnergy = energy;
        this.energyPool = energyPool;
        this.costOfSpecialAbility = 30;
    }
    @Override
    public void levelUp(){
        super.levelUp();
        this.costOfSpecialAbility = this.costOfSpecialAbility - 5;
        this.currEnergy = Math.min((int)((currEnergy + (currEnergy / 3))),energyPool);

    }

    @Override
    public void newTick(){
        currEnergy = currEnergy + 5;
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void specialAbility(List<Enemy> enemies) {
        if(currEnergy>costOfSpecialAbility){
            currEnergy -= costOfSpecialAbility;
            List<Enemy> enemiesInRange = enemies.stream()
                    .filter(e -> e.getPosition().range(this.position) ==1)
                    .toList();
            Random random = new Random();
            Enemy enemy = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
            enemy.battleSpecialAbility(attack()*level,enemy.defend());
            if(!enemy.alive()) {
                addExperience(enemy.experienceValue());
                enemy.onDeath();
            }
        }
    }


}
