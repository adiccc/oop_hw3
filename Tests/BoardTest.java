import control.initializers.TileFactory;
import model.game.Board;
import model.game.input.InputProvider;
import model.game.input.InputReaderScanncer;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.Position;
import utils.generators.FixedGenerator;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {
    @Test
    public void moveTile(){
        Board board = new Board();
        TileFactory tileFactory=new TileFactory();
        Enemy e=tileFactory.produceEnemy('Q');
        Tile empty=tileFactory.produceEmpty(new Position(0,0));
        Tile wall=tileFactory.produceWall(new Position(2,0));
        e.initialize(new Position(1,0),new FixedGenerator(),null,null);
        Player p= tileFactory.producePlayer(1,new InputReaderScanncer());
        p.initialize(new Position(1,1),new FixedGenerator(),null,null);
        List<Tile> tiles=new ArrayList<>();
        tiles.add(empty);
        tiles.add(wall);
        tiles.add(e);
        tiles.add(p);
        List<Enemy> enemies=new ArrayList<>();
        enemies.add(e);
        board.build(tiles,p,enemies,2);
        board.moveTile(e, InputProvider.Left);
        Assert.assertEquals("the board hasnt enable a valid move of enemy to enmpty tile",new Position(0,0),e.getPosition());
        board.moveTile(e, InputProvider.Right);
        board.moveTile(e, InputProvider.Right);
        Assert.assertEquals("the board enable an unvalid move of enemy to wall tile",new Position(1,0).toString(),e.getPosition().toString());
    }

    @Test
    public void removeEnemy(){
        Board board = new Board();
        TileFactory tileFactory=new TileFactory();
        Enemy e=tileFactory.produceEnemy('B');
        e.initialize(new Position(1,0),new FixedGenerator(),()-> board.removeEnemy(e,tileFactory.produceEmpty(new Position(-1,-1))),null);
        Player p= tileFactory.producePlayer(1,new InputReaderScanncer());
        p.initialize(new Position(1,1),new FixedGenerator(),null,null);
        List<Tile> tiles=new ArrayList<>();
        tiles.add(e);
        tiles.add(p);
        List<Enemy> enemies=new ArrayList<>();
        enemies.add(e);
        board.build(tiles,p,enemies,2);
        p.visit(e);
        Assert.assertEquals("the enemy wasn't remove after killed in a battle",0,board.getEnemies().size());
    }
}
