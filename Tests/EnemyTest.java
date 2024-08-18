package Tests;

import control.initializers.TileFactory;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.Position;
import utils.generators.FixedGenerator;

public class EnemyTest {

    @Test
    public void enemyTookDemage(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,null);
        player.initialize(new Position(0,1),new FixedGenerator(),null,null);
        player.battle(enemy);
        Assert.assertTrue("enemy 's' died after a battel with player 1 and he shouldn't have", enemy.alive());
        Assert.assertEquals("enemy should take 16 heath demage and didnt ","66/80",enemy.getHealth());
    }

    public void enemyLoose(){
        //TODO
    }

}
