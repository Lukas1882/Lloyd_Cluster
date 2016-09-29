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
        for(int i = 1;i<=100;i++){
            for(int j = 0;j<100;j++){
                points.add(new Point(i,j));
            }
        }

    }
    @Test
    public void RunCluster(){

        c1.setCentroid(new Point(25.25,25.25));
        c4.setCentroid(new Point(75.75,75.75));
        c2.setCentroid(new Point(25.25,75.75));
        c3.setCentroid(new Point(75.75,25.25));
        clusters.add(c1);
        clusters.add(c2);
        clusters.add(c3);
        clusters.add(c4);

        LloydRunner runner = new LloydRunner(clusters,points);
        int gen = runner.runLloydClusters();
        int i =0;
    }

    @Test
    public void RunCluster2(){

        c1.setCentroid(new Point(25.25,25.25));
        c4.setCentroid(new Point(75.75,75.75));
        c2.setCentroid(new Point(25.25,75.75));
        c3.setCentroid(new Point(75.75,25.25));
        clusters.add(c1);
        clusters.add(c2);
        clusters.add(c3);
        clusters.add(c4);

        LloydRunner runner = new LloydRunner(clusters,points);
        int gen = runner.runLloydClusters(20);
        int i =0;
    }
}
