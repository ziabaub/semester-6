import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;
import java.util.List;


public class Points {
    private List<Point2D> point2DS;
    private int size;

    public Points(int size) {
        this.size = size;
        point2DS = new ArrayList<>(size);
    }

    public  Point2D getPoints(int index) {
        return point2DS.get(index);
    }

    public int getSize() {
        return size;
    }


    public void add(Point2D point2D) {
        if(point2DS.size()<size) {
            point2DS.add(point2D);
        }
    }
}
