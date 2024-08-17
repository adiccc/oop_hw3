package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player   {
    private int remainingCoolDown ;
    private int abilityCoolDown;
    private int manaPool; // holds the max value of mana;
    private int currentMana; // manaPool/4;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(String name, int hitPoints, int attack, int defense , int manaPool,int manaCost ,int spellPower, int hitsCount,int abilityRange) {
        super(name, hitPoints, attack, defense);
        this.manaPool = manaPool;
        this.currentMana = manaPool/4;
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

    @Override
    public void visit(Player p) {

    }
    

    public void specialAbility(List<Enemy> enemies) {
        int hits = 0;
        List<Enemy> enemiesInRange = enemies.stream()
                .filter(e -> e.getPosition().range(this.position) <=  this.spellPower)
                .toList();
        while (hits<hitsCount&& !enemiesInRange.isEmpty()) {
            Random random = new Random();
            Enemy enemy = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
            enemy.battleSpecialAbility(spellPower,enemy.defend());
            if(!enemy.alive()){
                addExperience(enemy.experienceValue());
                enemy.onDeath();
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
    public <T> void casAbility(List<T> units) {
        specialAbility((List<Enemy>) units);
    }
}
