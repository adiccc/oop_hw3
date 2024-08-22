

import control.initializers.TileFactory;
import model.game.Board;
import model.game.input.InputReaderFile;
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

public class EnemyTest {

    @Test
    public void enemyTookDemage(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        player.battle(enemy);
        Assert.assertTrue("enemy 's' died after a battel with player 1 and he shouldn't have", enemy.alive());
        Assert.assertEquals("enemy should take 16 heath demage and didnt ","71/80",enemy.getHealth());
    }

    @Test
    public void enemyLoose(){
        TileFactory tileFactory=new TileFactory();
        Board board=new Board();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),()->board.removeEnemy(enemy,tileFactory.produceEmpty(new Position(-1,-1))),(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),()->{},(x)->{});
        List<Tile> tile=new ArrayList<>();
        tile.add(player);
        tile.add(enemy);
        List<Enemy> e=new ArrayList<>();
        e.add(enemy);
        board.build(tile,player,e,2);
        for(int i=0;i<12;i++)
            enemy.accept(player);
        Assert.assertEquals("enemy should have been removed from the enemies list after death",0,board.getEnemies().size());
    }

}
