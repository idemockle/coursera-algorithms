import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import java.util.Iterator;
import java.util.LinkedList;

public class KdTree {
    private PointBST tree;
    private int size;

    public KdTree() {
        tree = new PointBST();
    }
    public boolean isEmpty() { return tree.point == null; }
    public int size() { return size; }
    public void insert(Point2D p) { tree.add(p); size++; }
    public boolean contains(Point2D p) { return tree.contains(p); }
    
    /**
     * Draws all of the points to standard draw in black and the subdivisions 
     * in red (for vertical splits) and blue (for horizontal splits). This 
     * method need not be efficient—it is primarily for debugging.
     */
    public void draw() {
        Iterator<PointBST> iter = tree.iterator();
        
        PointBST currBST = iter.next();
        double xMin = currBST.point.x();
        double xMax = currBST.point.x();
        double yMin = currBST.point.y();
        double yMax = currBST.point.y();
        
        while (iter.hasNext()) {
            currBST = iter.next();
            if (currBST.point.x() < xMin) { xMin = currBST.point.x(); }
            if (currBST.point.x() > xMax) { xMax = currBST.point.x(); }
            if (currBST.point.y() < yMin) { yMin = currBST.point.y(); }
            if (currBST.point.y() > yMax) { yMax = currBST.point.y(); }
        }
        
        double xRange = xMax - xMin;
        double yRange = yMax - yMin;
        
        if (Double.compare(xRange, yRange) > 0) {
            double yMid = (yMax + yMin) / 2.;
            yRange = xRange;
            yMin = yMid - yRange / 2.;
            yMax = yMid + yRange / 2.;
        } else if (Double.compare(xRange, yRange) < 0) {
            double xMid = (xMax + xMin) / 2.;
            xRange = yRange;
            xMin = xMid - xRange / 2.;
            xMax = xMid + xRange / 2.;
        }
        
        StdDraw.setXscale(xMin - xRange/20, xMax + xRange/20);
        StdDraw.setYscale(yMin - yRange/20, yMax + yRange/20);
        
        for (PointBST n : tree) {
            if (n.isVert) { drawVert(n, yMin, yMax); }
            else          { drawHori(n, xMin, xMax); }
        }
    }
    
    private void drawVert(PointBST node, double yMin, double yMax) {
        if (!node.isVert) { 
            throw new IllegalArgumentException("node passed to drawVert must be vertical");
        }
        
        PointBST up = node.parent;
        double[] bounds = {yMin, yMax};
        int i = 0;
        while (up != null && i < 2) {
            if (!up.isVert) {
                if (isBetween(up.point.y(), yMin, node.point.y())) {
                    bounds[0] = up.point.y();
                } else if (isBetween(up.point.y(), node.point.y(), yMax)) {
                    bounds[1] = up.point.y();
                }
                i++;
            }
            up = up.parent;
        }
        
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.005);
        StdDraw.line(node.point.x(), bounds[0], node.point.x(), bounds[1]);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        StdDraw.point(node.point.x(), node.point.y());
    }
    
    private void drawHori(PointBST node, double xMin, double xMax) {
        if (node.isVert) { 
            throw new IllegalArgumentException("node passed to drawHori must not be vertical");
        }
        
        PointBST up = node.parent;
        double[] bounds = {xMin, xMax};
        int i = 0;
        while (up != null && i < 2) {
            if (up.isVert) {
                if (isBetween(up.point.x(), xMin, node.point.x())) {
                    bounds[0] = up.point.x();
                } else if (isBetween(up.point.x(), node.point.x(), xMax)) {
                    bounds[1] = up.point.x();
                }
                i++;
            }
            up = up.parent;
        }
        
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(.005);
        StdDraw.line(bounds[0], node.point.y(), bounds[1], node.point.y());
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        StdDraw.point(node.point.x(), node.point.y());
    }
    
    private boolean isBetween(double n, double min, double max) {
        return (Double.compare(n, min) > 0 && Double.compare(n, max) < 0);
    }
    
    /**
     * all points that are inside the rectangle 
     * @param rect - the rectangle to search for points in
     * @return Iterable which returns the points inside the rectangle
     */
//    public Iterable<Point2D> range(RectHV rect)
//    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
    
    
    private class PointBST implements Iterable<PointBST> {
        PointBST left;
        PointBST right;
        PointBST parent;
        Point2D point;
        boolean isVert;
        
        public PointBST() {
            isVert = true;
        }
        
        public PointBST(PointBST parent, boolean isVert) {
            this.parent = parent;
            this.isVert = isVert;
        }
        
        public void add(Point2D p) {
            if (point == null) {
                point = p;
            } else {
                if (p.equals(point)) { return; }
                
                double pDim;
                double pointDim;
                
                if (isVert) {
                    pDim = p.x();
                    pointDim = point.x();
                } else {
                    pDim = p.y();
                    pointDim = point.y();
                }
                
                if (Double.compare(pDim, pointDim) < 0) {
                    if (left == null) {
                        left = new PointBST(this, !this.isVert);
                    }
                    left.add(p);
                } else {
                    if (right == null) {
                        right = new PointBST(this, !this.isVert);
                    }
                    right.add(p);
                }
            }
        }
        
        public boolean contains(Point2D p) {
            if (point == null) { return false; }
            if (p == null) { throw new NullPointerException(); }
            if (point.equals(p)) { return true; }
            
            boolean isLess;
            if (isVert) {
                isLess = p.x() < point.x();
            } else {
                isLess = p.y() < point.y();
            }
            
            if (isLess) {
                if (left == null) { return false; }
                else { return left.contains(p); }
            } else {
                if (right == null) { return false; }
                else { return right.contains(p); }
            }
        }
        
        public Iterator<PointBST> iterator() {
            return new Iter();
        }
        private class Iter implements Iterator<PointBST> {
            LinkedList<PointBST> queue;

            public Iter() {
                queue = new LinkedList<>();
                buildQueue(PointBST.this);
            }

            public boolean hasNext() { return !queue.isEmpty(); }
            public PointBST next() { return queue.remove(); }

            private void buildQueue(PointBST p) {
                if (p.point != null) { queue.add(p); }
                if (p.left != null) { buildQueue(p.left); }
                if (p.right != null){ buildQueue(p.right); }
            }
        }
    }
    
    public static void main(String[] args) {
        KdTree tree = new KdTree();
        
        tree.insert(new Point2D(.7,.2));
        tree.insert(new Point2D(.5,.4));
        tree.insert(new Point2D(.2,.3));
        tree.insert(new Point2D(.4,.7));
        tree.insert(new Point2D(.9,.6));
        tree.insert(new Point2D(.5,.5));
        tree.draw();
//        for (PointBST p : tree.tree) {
//            System.out.println(p.point);
//        }
    }
}