package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;

public class Rogue extends Player {
    private int energy;
    private int cost;
    private int currentEnergy;

    public Rogue(String name, int hitPoints, int attack, int defense, int cost) {
        super(name, hitPoints, attack, defense);
        this.energy = 100;
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
            //your massage here;
        }
        else{

            for (Enemy e : enemies){
                if(e.getPosition().range(this.position)<=2){
                    currentEnergy = currentEnergy - cost;
                    this.battleSpecialAbility(Integer.parseInt(this.getAttack()), e.defend());
                    if(!e.alive()){
                        addExperience(e.experienceValue());
                        e.onDeath();
                    }
                }


            }
        }


    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public <T> void casAbility(List<T> units) {
        specialAbility((List<Enemy>) units);
    }
}
