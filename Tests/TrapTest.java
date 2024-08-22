import control.initializers.TileFactory;
import model.game.input.InputReaderScanncer;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.Position;
import utils.generators.FixedGenerator;

public class TrapTest {

    @Test
    public void turn(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('Q');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(1,0),new FixedGenerator(),null,(x)->{});
        enemy.turn(player);
        Assert.assertEquals("trap should have been visiable after 1 game tick ", "Q",enemy.toString());
        enemy.turn(player);
        enemy.turn(player);
        enemy.turn(player);
        enemy.turn(player);
        enemy.turn(player);
        Assert.assertEquals("trap should have been invisiable after 6 game tick",".",enemy.toString());
        for(int i=0;i<7;i++)
            enemy.turn(player);
        Assert.assertEquals("trap should have been visiable after 7 game tick of invisbilty", "Q",enemy.toString());
    }

    @Test
    public void attackPlayer(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('Q');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        enemy.turn(player);
        Assert.assertEquals("enemy 'Q' didnt attack the player in his range", "378/400",player.getHealth());
        player.setPosition(new Position(8,8));
        enemy.turn(player);
        Assert.assertEquals("enemy 'Q' attack the player out of his range", "378/400",player.getHealth());
    }
}
