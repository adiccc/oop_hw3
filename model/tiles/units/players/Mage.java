package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player   {
    private int manaPool; // holds the max value of mana;
    private int currentMana; // manaPool/4;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;
    private final String ABILITY_NAME="Blizzard";


    public Mage(String name, int hitPoints, int attack, int defense , int manaPool,int manaCost ,int spellPower, int hitsCount,int abilityRange) {
        super(name, hitPoints, attack, defense);
        this.manaPool = manaPool;
        this.currentMana = manaPool/4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        manaPool = manaPool+ (25*level);
        currentMana = Math.min((currentMana+(manaPool/4)),manaPool);
        spellPower = spellPower + (10*level);
    }
    @Override
    public void newTick(){
        currentMana = Math.min((currentMana+1)*level,manaPool);
    }
    

    public void specialAbility(List<Enemy> enemies) {
        int hits = 0;
        List<Enemy> enemiesInRange = enemies.stream()
                .filter(e -> e.getPosition().range(this.position) <=  this.abilityRange)
                .toList();
        if (currentMana<manaCost){
            messageCallback.send(String.format("%s  tried to cast %s tried to cast %dwhich is less than the mana cost",getName(),ABILITY_NAME,currentMana));
            return;
        }
        messageCallback.send(String.format( "%s cast %s",getName() , ABILITY_NAME));
        while (hits<hitsCount&& !enemiesInRange.isEmpty()) {
            Random random = new Random();
            Enemy enemy = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
            int defense = enemy.defend();
            messageCallback.send(String.format( "%s rolled %d points ",enemy.getName(),defense));
            int damge = enemy.battleSpecialAbility(spellPower,defense);
            messageCallback.send(String.format("%s hit %s for %d ability damage",this.getName()  , enemy.getName() ,  damge ));
            if(!enemy.alive()){
                killEnemy(enemy);
                enemiesInRange.remove(enemy);
            }
            hits= hits + 1;
        }
        if (hits>0){
            currentMana = currentMana - manaCost;
        }
    }
    public int getCurrmana() {
        return currentMana;
    }
    @Override
    public String description() {
        return String.format("%s \t\tMana: %d / %d \t\tSpell Power: %d",super.description(), currentMana, manaPool,spellPower);
    }
}
