import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

/**
 *
 * @author bstornelli
 */
public class Main {

    private static final  double CLUSTER_RADIO = 0.01;
    private static final  double POINT_RADIO = 0.005;
    private static double LINE_RADIO = 0.00002;
    private static final  int SLEEP_TIME = 1;
    private static Color[] colors;
    private static Clusters clusters;
    private static Points points;
    private static GeomTools tools= new GeomTools();
    private static int[] closestCluster;
    private static int n = 20000;
    private static int t = 2000;
    private static int k = 20;
    private static int iter = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        StdDraw.setXscale(0, t);
        StdDraw.setYscale(0, t * 1.1);
        colors = tools.getRandomColors(k);
        points = new Points(n);
        clusters = new Clusters(k);
        tools.setRandomPoints(points);
        tools.setRandomClusters(clusters,points);
        closestCluster = new int[n];
        double[] clusterRadio;
        double[] distance = new double[n]; // Distance to the closest cluster's center



        StdDraw.setPenColor();
        StdDraw.textLeft(0, t * 1.1, "K-Means Clustering");
        StdDraw.textLeft(0, t * 1.05, "Iteration " + iter);
        StdDraw.textRight(t, t * 1.1, "n = " + n);
        StdDraw.textRight(t, t * 1.05, "k = " + k);
        StdDraw.show(0);
        drawPoints(false);
        StdDraw.show(0);
        StdDraw.show(0);
        drawClusters();
        StdDraw.show(0);

        int changed;
        do {
            ++iter;
            changed = 0;
            double[] newX = new double[k];
            double[] newY = new double[k];
            int[] sizeOfCluster = new int[k];
            clusterRadio = new double[k];

            for (int p = 0; p < n; ++p) {
                distance[p] = Double.POSITIVE_INFINITY;

                for (int c = 0; c < k; ++c) {
                    double d = clusters.getCluster(c).distanceTo(points.getPoints(p));
                    if (d < distance[p]) {
                        closestCluster[p] = c;
                        distance[p] = d;
                    }
                }
                newX[closestCluster[p]] += points.getPoints(p).x();
                newY[closestCluster[p]] += points.getPoints(p).y();
                if (distance[p] > clusterRadio[closestCluster[p]]) {
                    clusterRadio[closestCluster[p]] = distance[p];
                }
                ++sizeOfCluster[closestCluster[p]];
            }
            // Start the drawing
            draw();
            Thread.sleep(SLEEP_TIME);

            for (int i = 0; i < k; ++i) {
                newX[i] /= sizeOfCluster[i];
                newY[i] /= sizeOfCluster[i];
                Point2D newP;
                try {
                    newP = new Point2D(newX[i], newY[i]);
                } catch (IllegalArgumentException e) {
                    newP = clusters.getCluster(i);
                }
                if (!newP.equals(clusters.getCluster(i))) {
                    ++changed;
                }
                clusters.add(i,newP);
            }
        } while (changed > 0);

        draw();
    }

    private static void draw()  {

        StdDraw.show(0);
        StdDraw.clear();

        StdDraw.setPenColor();
        StdDraw.textLeft(0, t * 1.1, "K-Means Clustering");
        StdDraw.textLeft(0, t * 1.05, "Iteration " + iter);
        StdDraw.textRight(t, t * 1.1, "n = " + n);
        StdDraw.textRight(t, t * 1.05, "k = " + k);

        drawClusters();

        drawPoints(true);

        StdDraw.show(SLEEP_TIME / 2);
    }



    private static void drawPoints(boolean colored) {

        for (int i = 0; i < points.getSize(); ++i) {
            if (colored) {
                StdDraw.setPenColor(colors[closestCluster[i]]);
                StdDraw.setPenRadius(LINE_RADIO);
                points.getPoints(i).drawTo(clusters.getCluster(closestCluster[i]));
            }
            StdDraw.setPenRadius(POINT_RADIO);
            points.getPoints(i).draw();
        }
    }

    private static void drawClusters() {
        for (int i = 0; i < clusters.getSize(); ++i) {
            StdDraw.setPenRadius(CLUSTER_RADIO);
            StdDraw.setPenColor(colors[i]);
            clusters.getCluster(i).draw();
        }
    }

}