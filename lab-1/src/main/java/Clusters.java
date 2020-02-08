import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;
import java.util.List;


public class Clusters {
    private List<Point2D> clusterP;
    private int size;

    public Clusters(int size) {
        this.size = size;
        clusterP = new ArrayList<>(size);
        init();
    }

    public  Point2D getCluster(int index) {
        return clusterP.get(index);
    }

    public int getSize() {
        return size;
    }

    public void add (Point2D point2D){
        if (clusterP.size()<size) {
            clusterP.add(point2D);
        }
    }

    public void add(int index, Point2D point2D){
        if (index<size){
            clusterP.add(index,point2D);
        }
    }


    private void init(){
        for (int i = 0; i < size; ++i) {
            double x = random(size);
            double y = random(size);
            clusterP.add(new Point2D(x, y));
        }
    }

    private static double random(int t) {
        return StdRandom.uniform() * t;
    }
}
