
import control.initializers.TileFactory;
import model.game.input.InputProvider;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.*;
import org.junit.Test;
import org.junit.Assert;
import utils.Position;
import utils.generators.FixedGenerator;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

    @Test
    public void onTick(){
        TileFactory tileFactory=new TileFactory(); // generate the tile factory for the game

        Player p=tileFactory.producePlayer(0,new InputReaderFile("./Tests/TestFiles/playerOnTick.txt")); // generate player
        p.initialize(new Position(0,0),new FixedGenerator(),null,null);



        Assert.assertEquals("the user input doesnt match the requested player move", InputProvider.Up.GetKey(),p.onTick().GetKey());
    }

    @Test
    public void playerTookDemage(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(0,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('M');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        enemy.battle(player);
        Assert.assertTrue("player '2' died after a battel with enemy 'M' and he shouldn't have", player.alive());
        Assert.assertEquals("player should take 27 heath demage and didnt ","272/300",player.getHealth());
    }

    @Test
    public void playerWonEnemyAttack(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(1,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('B');
        enemy.initialize(new Position(0,0),new FixedGenerator(),null,(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        enemy.battle(player);
        Assert.assertTrue("player '2' died after a battel with enemy 'B' and he shouldn't have", player.alive());
        Assert.assertEquals("player shouldnt take damage ","400/400",player.getHealth());
    }

    @Test
    public void playerWonItsAttack(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(5,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),()-> {},(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        player.visit(enemy);
        Assert.assertEquals("player should gain experience of the battle winning ",25,player.getExperience());
        Assert.assertEquals("player should get the enemy position","(0, 0)",player.getPosition().toString());
    }
    // check if the mage is attacking in a range of special ability + curr mana
    @Test
    public void mageSpecialAbility(){
        TileFactory tileFactory=new TileFactory();
        Player mage=tileFactory.producePlayer(2,new InputReaderScanncer());
        Enemy enemy1=tileFactory.produceEnemy('s');
        Enemy enemy2=tileFactory.produceEnemy('s');
        Enemy enemy3=tileFactory.produceEnemy('s');
        Enemy enemy4=tileFactory.produceEnemy('s');
        List<Enemy> enemies=new ArrayList<>();
        enemy1.initialize(new Position(1,0),new FixedGenerator(),()-> {},(x)->{});
        enemy2.initialize(new Position(3,2),new FixedGenerator(),()-> {},(x)->{});
        enemy3.initialize(new Position(2,0),new FixedGenerator(),()-> {},(x)->{});
        enemy4.initialize(new Position(0,0),new FixedGenerator(),()-> {},(x)->{});
        mage.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        enemies.add(enemy1);enemies.add(enemy2);enemies.add(enemy3);enemies.add(enemy4);
        mage.specialAbility(enemies);
        Mage m =(Mage) (mage);
        Assert.assertEquals("mage should have 45 currmana ",45,(m.getCurrmana()));
        Assert.assertTrue("enemies sould be hit", hitedEnemies(enemies));
    }

    private boolean hitedEnemies(List<Enemy> enemies) {
         for (Enemy e: enemies){
             String[] parts = e.getHealth().split("/");
             if(Integer.parseInt(parts[0])<80){
                 return true;
             }
         }
        return false;
    }
    //check if the enemy dies after the battle
    @Test
    public void mageSpecialAbility2(){
        TileFactory tileFactory=new TileFactory();
        Player mage=tileFactory.producePlayer(3,new InputReaderScanncer());
        Enemy enemy1=tileFactory.produceEnemy('s');
        List<Enemy> enemies=new ArrayList<>();
        enemy1.initialize(new Position(3,0),new FixedGenerator(),()-> {},(x)->{});
        mage.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        enemies.add(enemy1);
        mage.specialAbility(enemies);
        Mage m =(Mage)(mage);
        Assert.assertEquals("mage should have 45 currmana ",17,(m.getCurrmana()));
       Assert.assertEquals("solider should have","23/80" ,enemy1.getHealth());
    }



    @Test
    public void warriorSpecialAbility(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(0,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('k');
        enemy.initialize(new Position(0,0),new FixedGenerator(),()-> {},(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        List<Enemy> enemies=new ArrayList<>();
        enemies.add(enemy);
        player.specialAbility(enemies);
        Warrior w = (Warrior) player;
        Assert.assertEquals("Warrior should have 3 remainig cooldown",3,w.getRemainingCoolDown());
        Assert.assertEquals("Lannister Knight should have","174/200" ,enemy.getHealth());
    }

    @Test
    public void roughSpecialAbility(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(4,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('s');
        enemy.initialize(new Position(0,0),new FixedGenerator(),()-> {},(x)->{});
        player.initialize(new Position(0,1),new FixedGenerator(),null,(x)->{});
        List<Enemy> enemies=new ArrayList<>();
        enemies.add(enemy);
        player.specialAbility(enemies);
        Rogue w = (Rogue) player;
        Assert.assertEquals("Arye Starck should have energy : ",80, w.getCurrentEnergy());
        Assert.assertEquals(" Solider should have","41/80" ,enemy.getHealth());
    }


    @Test
    public void hunterSpecialAbility(){
        TileFactory tileFactory=new TileFactory();
        Player player=tileFactory.producePlayer(6,new InputReaderScanncer());
        Enemy enemy=tileFactory.produceEnemy('k'); // 80
        enemy.initialize(new Position(0,0),new FixedGenerator(),()-> {},(x)->{});
        player.initialize(new Position(0,4),new FixedGenerator(),null,(x)->{});
        List<Enemy> enemies=new ArrayList<>();
        enemies.add(enemy);
        player.specialAbility(enemies);
        Hunter hunter = (Hunter) player;
        Assert.assertEquals("hunter should have 9 current energy ",9,hunter.getCurrentArrow());
        Assert.assertEquals("Lannister Knight should have","174/200" ,enemy.getHealth());

    }
    private int healthToInteger(String health){
        String[] parts = health.split("/");
        return Integer.parseInt(parts[0]);
    }


}
