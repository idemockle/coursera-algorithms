import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Iterator;

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
        StdDraw.setPenRadius(.02);
        
        Iterator<Point2D> iter = pointSet.iterator();
        
        Point2D currPoint = iter.next();
        double xMin = currPoint.x();
        double xMax = currPoint.x();
        double yMin = currPoint.y();
        double yMax = currPoint.y();
        
        while (iter.hasNext()) {
            currPoint = iter.next();
            if (currPoint.x() < xMin) { xMin = currPoint.x(); }
            if (currPoint.x() > xMax) { xMax = currPoint.x(); }
            if (currPoint.y() < yMin) { yMin = currPoint.y(); }
            if (currPoint.y() > yMax) { yMax = currPoint.y(); }
        }
        
        double xRange = xMax - xMin;
        double yRange = yMax - yMin;
        
        StdDraw.setXscale(xMin - xRange/20, xMax + xRange/20);
        StdDraw.setYscale(yMin - yRange/20, yMax + yRange/20);
        pointSet.forEach( p -> StdDraw.point(p.x(), p.y()) );
    }
    
    /**
     * all points that are inside the rectangle 
     * @param rect - Rectangle to find points in
     * @return Iterable which iterates through Points inside rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        return new rangeIterable(rect);
    }
    
    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * @param p - the point to find the nearest neighbor of
     * @return point in PointSET which is closed to p
     */
    public Point2D nearest(Point2D p) {
        Point2D closest = null;
        double closestDist = Double.POSITIVE_INFINITY;
        for (Point2D setPoint : pointSet) {
            double dist = setPoint.distanceTo(p);
            if (dist < closestDist) {
                closest = setPoint;
                closestDist = dist;
            }
        }
        return closest;
    }
    
    private class rangeIterable implements Iterable<Point2D> {
        RectHV rect;
        
        public rangeIterable(RectHV rect) {
            this.rect = rect;
        }
        
        public Iterator<Point2D> iterator() {
            return new rangeIterator(rect);
        }
        
        private class rangeIterator implements Iterator<Point2D> {
            Iterator<Point2D> iter;
            RectHV rect;
            Point2D nextPoint;
            
            public rangeIterator(RectHV rect) {
                iter = PointSET.this.pointSet.iterator();
                this.rect = rect;
                nextPoint = null;
            }
            
            public boolean hasNext() { 
                if (nextPoint != null) {
                    return true;
                }
                while (iter.hasNext()) {
                    Point2D p = iter.next();
                    if (rect.contains(p)) {
                        nextPoint = p;
                        return true;
                    }
                }
                return false;
            }
            
            public Point2D next() {
                if (hasNext()) {
                    Point2D res = nextPoint;
                    nextPoint = null;
                    return res;
                }
                throw new java.util.NoSuchElementException();
            }
        }
    }
    
    public static void main(String[] args) {
        PointSET p = new PointSET();
        RectHV r = new RectHV(1, 1, 3, 5);
//        StdDraw.setPenRadius(.02);
//        StdDraw.point(2,2);
//        
        p.insert(new Point2D(0,0));
        p.insert(new Point2D(2,0));
        p.insert(new Point2D(2,3));
        p.insert(new Point2D(3,4));
        
        System.out.println(p.nearest(new Point2D(5,5)));
//        for (Point2D point : p.range(r)) {
//            System.out.println(point);
//        }
    }
    
}
