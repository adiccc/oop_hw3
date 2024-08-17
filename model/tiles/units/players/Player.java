package model.tiles.units.players;

import model.game.input.InputProvider;
import model.game.input.InputReader;
import model.tiles.Empty;
import model.tiles.Wall;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import utils.Position;

import java.util.List;

public abstract class  Player extends Unit {
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
        if(!e.alive()){
            addExperience(e.experienceValue());
            this.swapPosition(e);
            e.onDeath();
        }
    }

    public void visit(Wall w){
        // Do nothing
    }

    public void visit(Empty empty){
        swapPosition(empty);
    }

    @Override
    public void onDeath() {
        this.setTile('X');
       this.messageCallback.send("GAME OVER");

    }

    public abstract void newTick();
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

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }



}
