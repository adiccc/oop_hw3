
import control.initializers.LevelInitializer;
import model.game.input.InputReaderScanncer;
import model.tiles.Tile;
import org.junit.Test;
import org.junit.Assert;
import utils.Position;
import utils.generators.FixedGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class LevelInitTest {

    @Test
    public void unitPosition() {
        LevelInitializer levelInitializer=new LevelInitializer(1,new FixedGenerator(),new InputReaderScanncer(),(x)-> {});
        for (int i=1;i<=4;i++){
            String levelPath="./Levels/level"+i+".txt";
            levelInitializer.initLevel(levelPath);
            Map<Position, Tile> board=levelInitializer.buildBord().getBoard();
            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(levelPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int y = 0, x=0;
            for(String line : lines){
                x=0;
                for(char c : line.toCharArray()){
                    Position position = new Position(x, y);
                    Assert.assertTrue("tile at the position ("+x+","+y+") doesn't exist",
                            (board.containsKey(position)));
                    if(board.containsKey(position))
                        Assert.assertEquals("tile at the position ("+x+","+y+") isn't valid",
                                board.get(position).toString(),String.valueOf(c));
                    x++;
                }
                y++;
            }
        }
    }
}