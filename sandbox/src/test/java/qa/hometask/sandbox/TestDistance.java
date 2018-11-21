package qa.hometask.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDistance {

    @Test
    //check distance without long decimal part via AssertEquals
    public void testDistanceAbs() {
        Point p1 = new Point (1,0);
        Point p2 = new Point (2, 0);
        Assert.assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    //check distance with 0.01 precision for doubles with long decimal part
    public void testDistanceDif() {
        Point p1 = new Point (4,6);
        Point p2 = new Point (10,8);
        Assert.assertTrue((p1.distance(p2) - 6.32) < 0.01);
    }

    @Test
    //check that distance = 0.0 if coordinates are the same
    public void testDistance1Zero() {
        Point p1 = new Point (-3.5, -1.5);
        Point p2 = new Point (-3.5, -1.5);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

}
