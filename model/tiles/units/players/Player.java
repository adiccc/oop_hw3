package model.tiles.units.players;

import model.game.Level;
import model.game.input.InputProvider;
import model.game.input.InputReader;
import model.tiles.Empty;
import model.tiles.Wall;
import model.tiles.units.HeroicUnit;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import utils.Position;

import java.util.List;

public abstract class  Player extends Unit implements HeroicUnit {
    public static final char PLAYER_TILE = '@';
    protected static final int LEVEL_REQUIREMENT = 50;
    protected static final int HEALTH_GAIN = 10;
    protected static final int ATTACK_GAIN = 4;
    protected static final int DEFENSE_GAIN = 1;

    protected int level;
    protected int experience;
    protected InputReader inputReader;

    public Player(String name, int hitPoints, int attack, int defense) {
        super(PLAYER_TILE, name, hitPoints, attack, defense);
        this.level = 1;
        this.experience = 0;
    }

    public void addExperience(int experienceValue){
        this.experience += experienceValue;
        while (experience >= levelRequirement()) {
            levelUp();
        }
    }

    public void levelUp(){
        this.experience -= levelRequirement();
        this.level++;
        int healthGain = healthGain();
        int attackGain = attackGain();
        int defenseGain = defenseGain();
        health.increaseMax(healthGain);
        health.heal();
        attack += attackGain;
        defense += defenseGain;
        messageCallback.send(getName()+" reached level "+level+": +"+healthGain+" Health, +"+attackGain+" Attack, +"+defenseGain+" Defense ");
    }

    protected int levelRequirement(){
        return LEVEL_REQUIREMENT * level;
    }

    protected int healthGain(){
        return HEALTH_GAIN * level;
    }

    protected int attackGain(){
        return ATTACK_GAIN * level;
    }

    protected int defenseGain(){
        return DEFENSE_GAIN * level;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Enemy e){
        battle(e);
        if(!e.alive())
            killEnemy(e);
    }

    public void visit(Player p){
        //Do nothing
    }

    private void killEnemy(Enemy e){
        addExperience(e.experienceValue());
        this.swapPosition(e);
        e.onDeath();
        messageCallback.send(e.getName()+" died. "+getName()+" gained "+e.experienceValue()+" experience");
    }

    @Override
    public void onDeath() {
        this.setTile('X');
    }

    public abstract void specialAbility(List<Enemy> enemies);

    public void setPosition(Position position) {
        this.position = position;
    }

    public InputProvider onTick() {
        String playersMove = String.valueOf(inputReader.readPlayerMove());
        return InputProvider.FindByKey(playersMove);
    }

    public int getExperience() {
        return experience;
    }

    public String getExperienceForPrint(){
        return String.valueOf(getExperience());
    }

    public String description() {
        return super.description() +
                "\t\tLevel: " + level +
                "\t\tExperience: " + experience + "/" + levelRequirement();
    }

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public abstract void newTick();


}
