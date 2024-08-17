package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;

public class Monk extends Player{


    public Monk(String name, int hitPoints, int attack, int defense) {
        super(name, hitPoints, attack, defense);
    }

    @Override
    public void newTick() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void specialAbility(List<Enemy> enemies) {

    }
}
