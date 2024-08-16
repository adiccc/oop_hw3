
import control.initializers.TileFactory;
import model.game.input.InputProvider;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import org.junit.Test;
import org.junit.Assert;
import utils.Position;
import utils.generators.FixedGenerator;

public class PlayerTest {

    //TODO :
    // מעבר שלב
    // ניצחון סופי
    // on death
    // שימוש ביכולת

    @Test
    public void onTick(){
        TileFactory tileFactory=new TileFactory();
        Player p=tileFactory.producePlayer(1,new InputReaderFile("./Tests/TestFiles/playerOnTick.txt"));
        p.initialize(new Position(0,0),new FixedGenerator(),null,null);
        Assert.assertEquals("the user input doesnt match the requested player move", InputProvider.Up.GetKey(),p.onTick().GetKey());
    }

    @Test
    public void playerTookDemage(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('M');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,null);
        player.initialize(new Position(0,1),new FixedGenerator(),null,null);
        enemy.battle(player);
        Assert.assertTrue("player '2' died after a battel with enemy 'M' and he shouldn't have", player.alive());
        Assert.assertEquals("player should take 27 heath demage and didnt ","272/300",player.getHealth());
    }

    @Test
    public void playerWonEnemyAttack(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(2,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('B');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,null);
        player.initialize(new Position(0,1),new FixedGenerator(),null,null);
        enemy.battle(player);
        Assert.assertTrue("player '2' died after a battel with enemy 'B' and he shouldn't have", player.alive());
        Assert.assertEquals("player shouldnt take damage ","400/400",player.getHealth());
    }

    @Test
    public void playerWonItsAttack(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(6,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),()-> {},null);
        player.initialize(new Position(0,1),new FixedGenerator(),null,null);
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        Assert.assertEquals("player should gain experience of the battle winning ",25,player.getExperience());
        Assert.assertEquals("player should get the enemy position","(0, 0)",player.getPosition().toString());
    }

}
