package model.tiles.units.players;

public class Mage extends Player   {
    private int remainingCoolDown ;
    private int abilityCoolDown;
    private int manaPool; // holds the max value of mana;
    private int currentMana; // manaPool/4;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(String name, int hitPoints, int attack, int defense , int manaPool, int spellPower) {
        super(name, hitPoints, attack, defense);
        this.manaPool = manaPool;
        currentMana = manaPool/4;
        this.spellPower = spellPower;
        abilityRange = spellPower;
        // finish define the hitCount and manaCost.

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
    public void specialAbility(){
        currentMana = currentMana - manaCost;
        int hits = 0;
        while (hits<hitsCount ){

        }
    }
}
