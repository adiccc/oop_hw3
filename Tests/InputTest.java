
import model.game.input.InputReader;
import model.game.input.InputReaderFile;
import org.junit.Assert;
import org.junit.Test;

public class InputTest {
    @Test
    public void playerChoiceInput(){
        InputReader inputReaderFile=new InputReaderFile("./Tests/TestFiles/playerChoiceInput.txt");
        Assert.assertEquals("an input out of the regex is scanned",'2',inputReaderFile.readPlayerChoise(7));
    }
    @Test
    public void playerMoveInput(){
        InputReader inputReaderFile=new InputReaderFile("./Tests/TestFiles/playersMovesInput.txt");
        Assert.assertEquals("an input out of the regex is scanned",'s',inputReaderFile.readPlayerMove());
        Assert.assertEquals("an input out of the regex is scanned",'d',inputReaderFile.readPlayerMove());
        Assert.assertEquals("an input out of the regex is scanned",'a',inputReaderFile.readPlayerMove());
        Assert.assertEquals("an input out of the regex is scanned",'q',inputReaderFile.readPlayerMove());
    }
}
