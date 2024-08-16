import control.initializers.TileFactory;
import model.game.input.InputReaderFile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import utils.Position;

public class MonaterTest {

    public void inRange(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderFile("path"));
        player.setPosition(new Position(0,0));
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,1));
//        Assert.assertTrue(enemy.);
    }
}
