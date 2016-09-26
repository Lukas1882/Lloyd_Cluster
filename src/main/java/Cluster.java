/**
 * Created by lukelee on 9/25/16.
 */
import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by lukelee on 9/24/16.
 */
public class Cluster {
    private Point centroid;
    private LinkedList<Point> points = new LinkedList<>();
    private String historicalPoints;

    public  Cluster(){}

    public Cluster(double x, double y){
        setCentroid(new Point(x,y));
    }

    public String getHistoricalPoints() {
        return historicalPoints;
    }

    public void setHistoricalPoints(String historicalPoints) {
        this.historicalPoints = historicalPoints;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    public Point resetCenter(){
        if (points.size() == 0)
            return null;
        double xIndexSum =0, yIndexSum = 0;
        for (Point p : points){
            xIndexSum += p.getxIndex();
            yIndexSum += p.getyIndex();
        }
        centroid = new Point(xIndexSum/points.size(), yIndexSum/points.size());
        return centroid;
    }

    public String getPointsString(){
        if (points == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for(Point p : points){
            sb.append(p.getxIndex()+','+p.getyIndex()+';');
        }
        return  sb.toString();
    }

    public void updatePointsStringHistory(){
        historicalPoints = getPointsString();
    }

    public void sortPoints(){
        if (points == null)
            return;
        CompoundComparator<Point> cc = new CompoundComparator<Point>();
        cc.addComparator( Point.getDateComparator( SortOrder.ASCENDING ) );
        Collections.sort( points, cc );
    }

//    public  Boolean regenerateCluster(LinkedList<Cluster> clusters,LinkedList<Point> sourcePoints){
//
//        if (clusters == null || sourcePoints == null || clusters.size() == 0 || sourcePoints.size() == 0)
//            return null;
//        for(Cluster c : clusters){
//            c.sortPoints();
//            c.getPoints().clear();
//        }
//        for (Point p : sourcePoints){
//            double minDistance = clusters.get(0).getCentroid().getDistanceToPoint(p);
//            // Get the cluster for each of the points, set the cluster to each point
//            for(Cluster c : clusters){
//                if (p.getDistanceToPoint(c.getCentroid()) < minDistance)
//                    p.setCluster(c);
//            }
//            // update the cluster to cluster object
//            p.getCluster().getPoints().add(p);
//        }
//
//        // Check the clusters are changed or not
//        for(Cluster c : clusters){
//            c.sortPoints();
//            if (!c.getPointsString().equals(c.historicalPoints))
//              return true;
//        }
//        return false;
//    }
}

