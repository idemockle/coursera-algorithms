import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private TreeSet<Point2D> pointSet;
    
    /**
     * Creates empty set of points.
     */
    public PointSET() {
        pointSet = new TreeSet<Point2D>();
    }
    
    /**
     * @return boolean true if set is empty
     */
    public boolean isEmpty() { return pointSet.isEmpty(); }
    
    /**
     * @return number of points in the set
     */
    public int size() { return pointSet.size(); }
    
    /**
     * add the point to the set (if it is not already in the set)
     * @param p - the point to be added
     */
    public void insert(Point2D p) { pointSet.add(p); }
    
    /**
     * does the set contain point p? 
     * @param p - the point whose presence is checked for
     * @return true if Point2D p is present
     */
    public boolean contains(Point2D p) { return pointSet.contains(p); }
    
    /**
     * draw all points to standard draw
     */
    public void draw() {
//        StdDraw.enableDoubleBuffering();
        for (Point2D p : pointSet) {
            StdDraw.point(p.x(), p.y());
        }
//        StdDraw.show();
    }
    
//    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
//    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    public static void main(String[] args) {
        PointSET p = new PointSET();
        StdDraw.setXscale(1, 5);
        StdDraw.setYscale(2, 5);
        p.insert(new Point2D(1,2));
        p.insert(new Point2D(3,4));
        p.insert(new Point2D(5,5));
        p.draw();
    }
    
}
