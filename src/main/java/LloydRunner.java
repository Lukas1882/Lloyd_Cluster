import java.util.LinkedList;

/**
 * Created by lukelee on 9/25/16.
 */
public class LloydRunner {
    private LinkedList<Cluster> clusters;
    private LinkedList<Point> sourcePoints;
    public LloydRunner(LinkedList<Cluster> clusters, LinkedList<Point> sourcePoints){
        this.clusters = clusters;
        this.sourcePoints = sourcePoints;
    }

    public void runLloydClusters(){
        Boolean isChanged= true;
        while (isChanged){
            isChanged = runLloydClustersOnce();
        }
    }

    public Boolean runLloydClustersOnce(){
        Boolean isChanged = false;
        if (clusters == null || sourcePoints == null || clusters.size() == 0 || sourcePoints.size() == 0)
            return null;
        for(Cluster c : clusters){
            c.sortPoints();
            c.updatePointsStringHistory();
            if (c.getPoints() != null)
                c.getPoints().clear();
        }
        for (Point p : sourcePoints){
            //To intiate it, set the first cluster to the point
            double minDistance = clusters.get(0).getCentroid().getDistanceToPoint(p);
            p.setCluster(clusters.get(0));
            // Get the cluster for each of the points, set the cluster to each point
            for(Cluster c : clusters){
                if (p.getDistanceToPoint(c.getCentroid()) < minDistance)
                    p.setCluster(c);
            }
            // update the cluster to cluster object
            p.getCluster().getPoints().add(p);
        }

        // Update te resetCenter. Check the clusters are changed or not
        for(Cluster c : clusters){
            c.sortPoints();
            c.resetCenter();
            if (!c.getPointsString().equals(c.getHistoricalPoints()))
                isChanged = true;
        }
        return isChanged;
    }
}
