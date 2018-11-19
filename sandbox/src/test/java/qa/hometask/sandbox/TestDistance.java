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
    //check distance with 0.01 precision for doubles with long decimal part
    public void testDistance1Dif() {
        Point p = new Point (4,6,10, 8);
        Assert.assertTrue((p.distance() - 6.32) < 0.01);
    }

    @Test
    //check that distance = 0.0 if coordinates are the same
    public void testDistance1Zero() {
        Point p = new Point (-3,-1,-3, -1);
        Assert.assertEquals(p.distance(), 0.0);
    }

}
