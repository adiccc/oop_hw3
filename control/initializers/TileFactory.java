package control.initializers;

import model.game.input.InputReader;
import model.tiles.Empty;
import model.tiles.Tile;
import model.tiles.Wall;
import model.tiles.units.enemies.Boss;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.enemies.Monster;
import model.tiles.units.enemies.Trap;
import model.tiles.units.players.*;
import utils.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {
    private Player p;
    public static final List<Supplier<Player>> playerTypes = Arrays.asList(
            () -> new Warrior("Jon Snow", 300,30,4,3),
            () -> new Warrior("The Hound", 400,20,6,5),
            () -> new Mage("Melisandre", 100,5,1,300,30,15,5,6),
            () -> new Mage("Thoros of Myr",250,25,4,150,20,20,3,4),
            () -> new Rogue("Arya Stark",150,40,2,20),
            () -> new Rogue("Bronn", 250,35,3,50),
            () -> new Hunter("Ygritte", 220,30,2,6)

    );

    private Map<Character, Supplier<Enemy>> enemyTypes;
    public TileFactory(){
        enemyTypes = new HashMap<>();
        enemyTypes.put('s', () -> new Monster('s', "Lannister Soldier", 80, 8, 3, 3, 25));
        enemyTypes.put('k', () -> new Monster('k', "Lannister Knight", 200, 14, 8, 4, 50));
        enemyTypes.put('q', () -> new Monster('q', "Queen’s Guard", 400, 20, 15, 5, 100));
        enemyTypes.put('z', () -> new Monster('z', "Wright", 600, 30, 15, 3, 100));
        enemyTypes.put('b', () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 4, 250));
        enemyTypes.put('g', () -> new Monster('g', "Giant-Wright", 1500, 100, 40, 5, 500));
        enemyTypes.put('w', () -> new Monster('w', "White Walker", 2000, 150, 50, 6, 1000));
        enemyTypes.put('M', () -> new Boss('M', "The Mountain", 1000, 60, 25, 6, 500,5));
        enemyTypes.put('C', () -> new Boss('C', "Queen Cersei", 100, 10, 10, 1, 1000,8));
        enemyTypes.put('K', () -> new Boss('K', "Night’s King", 5000, 300, 150, 8, 500,3));
        enemyTypes.put('B', () -> new Trap('B', "Bonus Trap", 1,1,1,250,1,5));
        enemyTypes.put('Q', () -> new Trap('Q', "Queen’s Trap",250,50,10,100,3,7));
        enemyTypes.put('D', () -> new Trap('D', "Death Trap", 500,100,20,250,1,10));
    }

    public int getNumPlayer(){return playerTypes.size();}

    public Player producePlayer(int playerID, InputReader inputReader){
        Supplier<Player> supp = playerTypes.get(playerID);
        this.p = supp.get();
        this.p.setInputReader(inputReader);
        return this.p;
    }

    public Player producePlayer(Position position){
        p.setPosition(position);
        return this.p;
    }

    public Enemy produceEnemy(char tile){
        Enemy e = enemyTypes.get(tile).get();
        return e;
    }

    public Tile produceEmpty(Position p){
        return new Empty().initialize(p);
    }

    public Tile produceWall(Position p){
        return new Wall().initialize(p);
    }
}
