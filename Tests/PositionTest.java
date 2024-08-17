import org.junit.Assert;
import org.junit.Test;
import utils.Position;

public class PositionTest {

    @Test
    public void range(){
        Position p1 = new Position(0, 0);
        Position p2=new Position(8,0);
        Assert.assertTrue("the position(0,0) and (8,0) should be at the range of 8.0",8.0==p1.range(p2));
        p2=new Position(3,3);
        Assert.assertTrue("the position(0,0) and (3,3) should be at the range of 8.0",3.0<=p1.range(p2));
    }

    @Test
    public void isInRange(){
        Position p1 = new Position(0, 0);
        Position p2=new Position(8,0);
        Assert.assertFalse("the position(0,0) and (8,0) should not be at the range of 3",p1.isInRange(p2,3));
    }
}
