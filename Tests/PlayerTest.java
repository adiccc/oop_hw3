
import control.initializers.LevelInitializer;
import control.initializers.TileFactory;
import model.game.Board;
import model.game.Program;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Mage;
import model.tiles.units.players.Player;
import model.tiles.units.players.Rogue;
import model.tiles.units.players.Warrior;
import org.junit.Test;
import org.junit.Assert;
import utils.Position;
import utils.callbacks.MessageCallback;
import utils.generators.FixedGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerTest {


    MessageCallback messageCallback=(x)->System.out.println(x);

    @Test
    public void checkAllPlayersData(){
        TileFactory tileFactory=new TileFactory();
        List<Supplier<Player>> playerTypes = Arrays.asList(
                () -> new Warrior("Jon Snow", 300,30,4,3),
                () -> new Warrior("The Hound", 400,20,6,5),
                () -> new Mage("Melisandre", 100,5,1,300,30,15,5,6),
                () -> new Mage("Thoros of Myr",250,25,4,150,20,20,3,4),
                () -> new Rogue("Arya Stark",150,40,2,20),
                () -> new Rogue("Bronn", 250,35,3,50)
        );
        for (int i=0;i<7;i++)
            Assert.assertEquals("the properties of the input player number :"+i+" isn't match",tileFactory.producePlayer(i,new InputReaderScanncer()), playerTypes.get(i));
    }



    // מעבר שלב
    // ניצחון
    // הפסד
    // שימוש ביכולת


//    @Test
//    public void checkAllPlayersData() throws IOException {
//        try {
//            File myObj = new File("PlayerDataVlidation.txt");
//            FileWriter writer = new FileWriter("PlayerDataVlidation.txt");
//            for (int i=1;i<7;i++){
//                    myObj = new File("PlayerDataVlidation.txt");
//                    writer = new FileWriter("PlayerDataVlidation.txt");
//                    writer.write(String.valueOf(i));
//                    writer.close();
//                    playerDataValidation();
//            }
//
//        } catch (IOException e) {
////            System.out.println("An error occurred.");
//        }
//    }
//
//    private void playerDataValidation(){
//
//    }
}
