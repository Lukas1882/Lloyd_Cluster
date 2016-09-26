import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by lukelee on 9/25/16.
 */
public class Lloyd_Test {
    private static Cluster c1;
    private static Cluster c2;
    private static Cluster c3;
    private static Cluster c4;
    private static Cluster c5;
    private static LinkedList<Cluster> clusters;
    private static LinkedList<Point> points;


    @Before
    public void PrepareTest(){
        c1 = new Cluster();
        c2 = new Cluster();
        c3 = new Cluster();
        c4 = new Cluster();
        c5 = new Cluster();
        points = new LinkedList<>();
        clusters = new LinkedList<>();
//        for(int i = 0;i<100;i++){
//            for(int j = 0;j<100;j++){
//                points.add(new Point(i,j));
//            }
//        }

    }
    @Test
    public void OneCluster(){

        c1.setCentroid(new Point(2,2));
        clusters.add(c1);
        points.add(new Point(2,2));
        points.add(new Point(3,2));
        points.add(new Point(4,2));
        points.add(new Point(2,3));
        points.add(new Point(4,3));
        points.add(new Point(2,4));
        points.add(new Point(3,4));
        points.add(new Point(4,4));
        points.add(new Point(2,5));

        LloydRunner runner = new LloydRunner(clusters,points);
        runner.runLloydClusters();
        int i =11;


    }
}
