import control.initializers.TileFactory;
import model.tiles.Tile;
import org.junit.Assert;
import org.junit.Test;
import utils.Position;

public class TileTest {

    @Test
    public void swapPosition(){
        TileFactory tf = new TileFactory();
        Tile t1=tf.produceEmpty(new Position(0,0));
        Tile t2=tf.produceWall(new Position(0,1));
        t1.swapPosition(t2);
        Assert.assertEquals("tile didnt swap position", new Position(0,0), t2.getPosition());
    }
}
