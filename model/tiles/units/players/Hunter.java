package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Hunter extends Player{
    private int range;
    private int arrowsCount; // courent amount of arrows
    private int ticksCount;
    private final String ABILITY_NAME="Shoot";



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
                .filter(e -> e.getPosition().range(this.position) <=range)
                .toList();
        if(arrowsCount==0 || enemiesInRange.isEmpty()) {
            messageCallback.send(String.format("%s tried to cast %s , but your current arrows is: 0 or you dont have any enemy in range: %d",getName(),ABILITY_NAME,range) );
        }
        else if(arrowsCount>0){
            messageCallback.send(String.format("%s cast %s",getName(), ABILITY_NAME));
            arrowsCount = arrowsCount -1;
            Optional<Enemy> closestEnemy = enemiesInRange.stream().min((e1, e2) ->
                    Integer.compare(Double.valueOf(e1.getPosition().range(this.position)).intValue(), Double.valueOf(e2.getPosition().range(this.position)).intValue()));
            closestEnemy.ifPresent(enemy -> {
                // Call a method on the enemy object
                int defense = enemy.defend();
                messageCallback.send(String.format("%s rolled %d points",enemy.getName() , defense));
                int damage = enemy.battleSpecialAbility(Integer.parseInt(this.getAttack()),defense);
                messageCallback.send(String.format("%s hit %s for %d ability damage",this.getName() , enemy.getName(),damage ));
                if(!enemy.alive())
                    killEnemy(enemy);

            });
        }
    }

    public int getCurrentArrow() {
        return this.arrowsCount;
    }

    @Override
    public String description() {
        return String.format("%s \t\tArrows: %d",super.description(),arrowsCount);
    }
}
