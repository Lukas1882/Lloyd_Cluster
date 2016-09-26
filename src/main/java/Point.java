import javax.swing.*;
import java.util.Comparator;

import static com.sun.xml.internal.xsom.impl.UName.comparator;

/**
 * Created by lukelee on 9/24/16.
 */
public class Point  {
    private double xIndex;
    private double yIndex;
    private Cluster cluster;

    public Point(double x, double y){
        this.xIndex = x;
        this.yIndex = y;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public double getxIndex() {
        return xIndex;
    }

    public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public double getyIndex() {
        return yIndex;
    }

    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    public double getDistanceToPoint( Point point){
        if (this == point)
            return 0;
        double x_distance = this.getxIndex() - point.getxIndex();
        double y_distance = this.getyIndex() - point.getyIndex();
        return Math.sqrt( x_distance*x_distance + y_distance*y_distance);
    }

    public static double getDistance(Point apoint, Point bpoint){
        if (apoint == bpoint)
            return 0;
        double x_distance = apoint.getxIndex() - bpoint.getxIndex();
        double y_distance = apoint.getyIndex() - bpoint.getyIndex();
        return Math.sqrt( x_distance*x_distance + y_distance*y_distance);
    }


    public static Comparator<Point> getDateComparator(final SortOrder sortOrder ) {

        Comparator<Point> CompoundComparator = new CompoundComparator<Point>() {

            @Override
            public int compare( Point p1, Point p2 ) {
                if( p1.getxIndex() > p2.getxIndex() )
                    return 1;
                if( p1.getxIndex() < p2.getxIndex() )
                    return -1;
                if( p1.getyIndex() > p2.getyIndex() )
                    return 1;
                if( p1.getyIndex() < p2.getyIndex() )
                    return -1;
                return 0;
            }
        };
        return CompoundComparator;
    }



}

