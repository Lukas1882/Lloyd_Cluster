/**
 * Created by lukelee on 9/25/16.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class Point_Test {
    private static Point point;

    @Before
    public void PrepareForTest(){
        double x= 5;
        double y = 1;
        point = new Point(x,y);
    }

    @Test
    public void CreatePoint_Test() throws Exception {
        // Create point

        double x= 5;
        double y = 1;
        point = new Point(x,y);
        Assert.assertEquals(Double.toString(x), Double.toString(point.getxIndex()));
        Assert.assertEquals(Double.toString(y), Double.toString(point.getyIndex()));
    }
    @Test
    public void ClusterSetting_Test() throws Exception {
        Cluster cluster = new Cluster();
        point.setCluster(cluster);
        Assert.assertEquals(cluster,point.getCluster());
    }

    @Test
    public void PointDitance_Test() throws Exception {
        double x = 2;
        double y = 1;
        double dis = Math.sqrt( Math.pow(2,point.getxIndex() - x) + Math.pow(2,(point.getyIndex() - y)));
        assertEquals(Double.toString(dis), Double.toString(point.getDistanceToPoint(new Point(x,y))));
        assertEquals(Double.toString(dis), Double.toString(Point.getDistance(point, new Point(x,y) )));
    }

}

