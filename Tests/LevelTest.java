import control.initializers.TileFactory;
import model.game.Board;
import model.game.Level;
import model.game.input.InputReaderFile;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Test;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class LevelTest {

    @Test
    public void startLevel(){
        Board board=new Board();
        TileFactory tileFactory=new TileFactory();
        Player p=tileFactory.producePlayer(1,new InputReaderFile("path"));
//        Enemy e= tileFactory.produceEnemy('s');
//        e.initialize(new Position(0,0),)
        List<Tile> tiles=new ArrayList<>();
        tiles.add(p);
        board.build(tiles,p,new ArrayList<>(),2);
        //TODO: player need to kill the enemy and tan the level can end
        Level level=new Level(board,null);

    }
}
