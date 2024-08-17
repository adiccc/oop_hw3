package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Hunter extends Player{
    private int range;
    private int arrowsCount;
    private int ticksCount;


    public Hunter (String name, int hitPoints, int attack, int defense, int range) {
        super(name, hitPoints, attack, defense);
        this.range = range;
        this.arrowsCount = this.level*10;
        this.ticksCount = 0;
    }
    @Override
    public void levelUp(){
        super.levelUp();
        arrowsCount = arrowsCount + 10*level;
        attack = attack + 2*level;
        defense = defense + level;


    }

    @Override
    public void newTick(){
        if(ticksCount == 10){
            arrowsCount = arrowsCount +level;
            ticksCount = 0;
        }
        else{
            ticksCount = ticksCount +1 ;
        }
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void specialAbility(List<Enemy> enemies) {
        List<Enemy> enemiesInRange = enemies.stream()
                .filter(e -> e.getPosition().range(this.position) <=6)
                .toList();
        if(arrowsCount==0 || enemiesInRange.isEmpty()) {
            // write error massage;
        }
        else if(arrowsCount>0){
            arrowsCount = arrowsCount -1;
            Optional<Enemy> closestEnemy = enemiesInRange.stream().min((e1, e2) ->
                    Integer.compare((int) e1.getPosition().range(this.position), (int) e2.getPosition().range(this.position)));

            closestEnemy.ifPresent(enemy -> {
                // Call a method on the enemy object
                enemy.battleSpecialAbility(Integer.parseInt(this.getAttack()),enemy.defend());
                if(!enemy.alive()) {
                    addExperience(enemy.experienceValue());
                    enemy.onDeath();
                }
            });


        }
    }

    public int getArrowsCount(){
        return arrowsCount;
    }


    public int getCurrentArrow() {
        return this.arrowsCount;
    }

    @Override
    public <T> void casAbility(List<T> units) {
        specialAbility((List<Enemy>) units);
    }
}
