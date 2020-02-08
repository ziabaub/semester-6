import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.*;


public class GeomTools {

    public Color[] getRandomColors(int n) {
        Color[] colors = new Color[n];
        for (int i = 0; i < n; i++) {
            colors[i] = Color.getHSBColor((float)StdRandom.uniform(), 0.85f, 1.0f);
        }
        return colors;
    }

    public void setRandomClusters(Clusters clusters , Points points){
        int len = clusters.getSize();
        for (int i = 0; i < len; ++i) {
            clusters.add(points.getPoints(i));
        }
    }
    public void setRandomPoints(Points point2DS){

        int len = point2DS.getSize();
        for (int i = 0; i < len; ++i) {
            double x = random();
            double y = random();
            point2DS.add(new Point2D(x, y));
        }
    }

    private static double random() {
        return StdRandom.uniform() * 2000;
    }
}
