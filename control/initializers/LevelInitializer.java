package control.initializers;

import model.game.Board;
import model.game.input.InputReader;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.Position;
import utils.callbacks.DeathCallback;
import utils.callbacks.MessageCallback;
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
    private Generator generator;
    private Board board;
    private MessageCallback messageCallback;

    public LevelInitializer(int playerID, Generator generator, InputReader inputReader, MessageCallback messageCallback){
        this.messageCallback=messageCallback;
        this.board=new Board();
        this.tileFactory = new TileFactory();
        this.player=tileFactory.producePlayer(playerID,inputReader);
        this.generator=generator;
    }

    public Board getBoard(){
        return board;
    }
    public void initLevel(String levelPath){
        if(!player.alive())
            board=null;
        else {
            List<Enemy> enemies = new ArrayList<>();
            List<Tile> tiles = new ArrayList<>();
            int width = -1;
            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(levelPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int y = 0, x = 0;
            for (String line : lines) {
                x = 0;
                for (char c : line.toCharArray()) {
                    Position position = new Position(x, y);
                    switch (c) {
                        case '.':
                            tiles.add(this.tileFactory.produceEmpty(position));
                            break;
                        case '#':
                            tiles.add(this.tileFactory.produceWall(position));
                            break;
                        case '@':
                            tiles.add(this.tileFactory.producePlayer(position));
                            player.initialize(position, generator, () -> player.onDeath(), messageCallback);
                            break;
                        default:
                            Enemy e = this.tileFactory.produceEnemy(c);
                            e.initialize(position, generator, () -> board.removeEnemy(e, this.tileFactory.produceEmpty(new Position(-1, -1))), messageCallback);
                            tiles.add(e);
                            enemies.add(e);
                            break;
                    }
                    x++;
                }
                y++;
            }
            width = x;
            this.board = board.build(tiles, player, enemies, width);
        }
    }
}
