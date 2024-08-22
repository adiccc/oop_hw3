
import control.initializers.TileFactory;
import model.game.input.InputProvider;
import model.game.input.InputReaderScanncer;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.Position;
import utils.generators.FixedGenerator;

public class MonaterTest {

    @Test
    public void turn(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(1,0),new FixedGenerator(),null,(x)->{});
        Assert.assertEquals("monster should have chase the player in its range ", InputProvider.Right,enemy.turn(player));
        player.setPosition(new Position(8,5));
        Assert.assertEquals("monster shouldn't have chase the player, its out of his range ", InputProvider.Left,enemy.turn(player));
    }
}
