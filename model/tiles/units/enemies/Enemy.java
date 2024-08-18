package model.tiles.units.enemies;

import model.game.input.InputProvider;
import model.tiles.Empty;
import model.tiles.Wall;
import model.tiles.units.HeroicUnit;
import model.tiles.units.Unit;
import model.tiles.units.players.Player;
import utils.Position;
import utils.generators.Generator;

import java.util.List;

public abstract class Enemy extends Unit implements HeroicUnit {
    protected int experienceValue;

    public Enemy(char tile, String name, int hitPoints, int attack, int defense, int experienceValue) {
        super(tile, name, hitPoints, attack, defense);
        this.experienceValue = experienceValue;
    }

    public int experienceValue() {
        return experienceValue;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Enemy e){
        // Do nothing
    }

    public void visit(Wall w){
        // Do nothing
    }

    public void visit(Empty empty){
        swapPosition(empty);
    }

    public void visit(Player p){
        battle(p);
        if (!p.alive()){
            p.onDeath();
        }
    }
    public <T> void casAbility(List<T> units){

    }
    public abstract InputProvider turn(Player player);

    protected abstract boolean isInRange(Player player);


}
