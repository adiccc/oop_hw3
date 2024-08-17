import org.junit.Assert;
import org.junit.Test;
import utils.Health;

public class HealthTest {

    @Test
    public void takeDamage(){
        Health h=new Health(200);
        Assert.assertEquals("unit cant take more damage that its current health",200,h.takeDamage(300));
    }

    @Test
    public void updateOnCast(){
        Health h=new Health(200);
        h.takeDamage(200);
        h.updateCurrentHealthOnCast(30);
        Assert.assertEquals("unit cant be updated for more its capacity health",200,h.getCurrent());
    }
}
