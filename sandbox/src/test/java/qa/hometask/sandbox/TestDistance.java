package qa.hometask.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDistance {

    @Test
    public void testDistanceAbs() {
        Point p = new Point (1,0,2, 0);
        Assert.assertEquals(p.distance(), 1.0);
    }

    @Test
    public void testDistance1Dif() {
        Point p = new Point (4,6,10, 8);
        Assert.assertTrue((p.distance() - 6.32) < 0.01);
    }

}
