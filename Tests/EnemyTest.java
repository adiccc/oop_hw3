import control.initializers.TileFactory;
import model.game.input.InputReaderFile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import org.junit.Test;

public class EnemyTest {

    @Test
    public void enemyTookDemage(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderFile("path"));
        Enemy enemy=tileFactory.produceEnemy('s');
        player.battle(enemy);
        Assert.assertTrue("enemy 's' died after a battel with player 1 and he shouldn't have", enemy.alive());
        Assert.assertEquals("enemy should take 16 heath demage and didnt ","66/80",enemy.getHealth());
    }

    public
}
