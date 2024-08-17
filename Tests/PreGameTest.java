import model.game.PreGame;
import model.game.input.InputReaderFile;
import org.junit.Assert;
import org.junit.Test;

public class PreGameTest {
    @Test
    public void getPlayerId(){
        PreGame preGame = new PreGame((x)->{},new InputReaderFile("./Tests/TestFiles/playerIdChosenInPreGame.txt"));
        Assert.assertEquals("the user chose the player id represents player 1",1,preGame.getPlayerId());
    }
}
