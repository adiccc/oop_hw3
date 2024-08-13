package control.initializers;

import model.game.Board;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.Position;
import utils.generators.Generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelInitializer {
    private Player player;
    private TileFactory tileFactory;
    private List<Enemy> enemies;
    private List<Tile> tiles;
    private int width;
    private Generator generator;

    public LevelInitializer(int playerID, Generator generator){
        this.tileFactory = new TileFactory();
        this.enemies = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.player=tileFactory.producePlayer(playerID);
        this.width=-1;
        this.generator=generator;
    }

    public Board buildBord(){
        return new Board(tiles,player,enemies,width);
    }
    public void initLevel(String levelPath){
        this.tiles=new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(levelPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int y = 0, x=0;
        for(String line : lines){
            x=0;
            for(char c : line.toCharArray()){
                Position position = new Position(x, y);
                switch(c) {
                    case '.':
                        this.tiles.add(this.tileFactory.produceEmpty(position));
                        break;
                    case '#':
                        this.tiles.add(this.tileFactory.produceWall(position));
                        break;
                    case '@':
                        this.tiles.add(this.tileFactory.producePlayer());
                        player.setPosition(position);
                        break;
                    default:
                        Enemy e=this.tileFactory.produceEnemy(c,position,null,generator,null);
                        this.tiles.add(e);
                        this.enemies.add(e);
                        break;
                }
                x++;
            }
            y++;
        }
        this.width=x;
    }
}
