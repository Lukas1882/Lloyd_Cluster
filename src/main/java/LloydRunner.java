import java.util.LinkedList;
import java.util.Stack;

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

    public int runLloydClusters(){
        Boolean isChanged= true;
        int gen =0;
        while (isChanged){
            isChanged = runLloydClustersOnce();
            gen++;
        }
        return gen;
    }

    public int runLloydClusters(int limit){
        Boolean isChanged= true;
        int gen =0;
        while (isChanged){
            isChanged = runLloydClustersOnce(limit);
            gen++;
        }
        return gen;
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
                if (p.getDistanceToPoint(c.getCentroid()) < minDistance) {
                    minDistance = p.getDistanceToPoint(c.getCentroid());
                    p.setCluster(c);
                }
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

    // This is the Lloyd with point limitation in cluster
    public Boolean runLloydClustersOnce(int limit){
        Boolean isChanged = false;
        if (clusters == null || sourcePoints == null || clusters.size() == 0 || sourcePoints.size() == 0)
            return null;
        for(Cluster c : clusters){
            c.sortPointsByDistance();
            c.updatePointsStringHistory();
            // Clean the last points for each cluster
            if (c.getPoints() != null)
                c.getPoints().clear();
        }
        for (Point p : sourcePoints){
            //To intiate it, set the first cluster to the point
            double minDistance=0;
            p.setCluster(clusters.get(0));

            // get a list of cluster which available to add
            Stack<Point> competors = new Stack<>();
            Point pinPoint;
            boolean isNew = true;
            competors.add(p);
            while (!competors.isEmpty()){
                pinPoint = competors.pop();
                for(Cluster c : clusters){
                    // if the points not full
                    if (c.getPoints().size()<limit-1){
                       if (isNew){
                           minDistance = c.getCentroid().getDistanceToPoint(pinPoint);
                           pinPoint.setCluster(c);
                           isNew = false;
                       } else if(c.getCentroid().getDistanceToPoint(pinPoint) < minDistance){
                           minDistance = c.getCentroid().getDistanceToPoint(pinPoint);
                           c.getPoints().add(pinPoint);
                           pinPoint.setCluster(c);
                           c.sortPointsByDistance();
                       }
                    }
                    // if the points are full
                    else{
                        if(c.getPoints().get(limit-1).getDistanceToPoint(c.getCentroid()) > pinPoint.getDistanceToPoint(c.getCentroid())){
                            competors.add(c.getPoints().get(limit-1));
                            c.getPoints().set(limit-1,pinPoint);
                            c.getPoints().add(pinPoint);
                            pinPoint.setCluster(c);
                            c.sortPointsByDistance();
                            if (isNew){
                                isNew = false;
                                minDistance = pinPoint.getDistanceToPoint(c.getCentroid());
                            }
                            else if(pinPoint.getDistanceToPoint(c.getCentroid()) < minDistance)
                                minDistance = pinPoint.getDistanceToPoint(c.getCentroid());
                        }
                    }
                }
            }
        }

        // Update te resetCenter. Check the clusters are changed or not
        for(Cluster c : clusters){
            c.sortPointsByDistance();
            c.resetCenter();
            if (!c.getPointsString().equals(c.getHistoricalPoints()))
                isChanged = true;
        }
        return isChanged;
    }
}
