import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import java.util.LinkedList;

/**
 * Created by lukelee on 9/25/16.
 */
public class Cluster_Test {
    private static Cluster cluster;
    private static LinkedList<Point> points;
    private static Point centroid;
    @Before
    public void prepareForTest(){
        cluster = new Cluster();
        points = new LinkedList<>();
        points.add(new Point(0,0));
        points.add(new Point(0,2));
        points.add(new Point(2,0));
        points.add(new Point(2,2));

        cluster.setPoints(points);
    }

    @Test
    public void GetCentroid_Test(){
        cluster.resetCenter();
        double xSum=0;
        double ySum = 0;
        for(Point p : points){
            xSum+= p.getxIndex();
            ySum += p.getyIndex();
        }

        centroid = cluster.getCentroid();
        Assert.assertEquals(Double.toString(centroid.getxIndex()), Double.toString(xSum/points.size()));
        Assert.assertEquals(Double.toString(centroid.getyIndex()), Double.toString(ySum/points.size()));
    }
}
