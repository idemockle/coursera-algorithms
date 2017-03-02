import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Iterator;
import java.util.Stack;

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
    public void draw() {
        Iterator<Point2D> iter = tree.iterator();
        
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
        tree.forEach( p -> StdDraw.point(p.x(), p.y()) );
    }
//    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
//    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
    
    
    private class PointBST implements Iterable<Point2D> {
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
        
        public Iterator<Point2D> iterator() {
            return new Iter();
        }
        private class Iter implements Iterator<Point2D> {
            Stack<Point2D> stack;

            public Iter() {
                stack = new Stack<>();
                buildStack(PointBST.this);
            }

            public boolean hasNext() { return !stack.empty(); }
            public Point2D next() { return stack.pop(); }

            private void buildStack(PointBST p) {
                if (p.point != null) { stack.push(p.point); }
                if (p.left != null) { buildStack(p.left); }
                if (p.right != null){ buildStack(p.right); }
            }
        }
    }
    
    public static void main(String[] args) {
        KdTree tree = new KdTree();
        
        tree.insert(new Point2D(1,2));
        tree.insert(new Point2D(0,0));
        tree.insert(new Point2D(3,4));
        tree.insert(new Point2D(9,2));
        
//        System.out.println(tree.tree.point);
//        System.out.println(tree.tree.right.point);
//        System.out.println(tree.tree.right.left.point);
//        System.out.println(tree.tree.right.left.parent.point);
//        System.out.println(tree.tree.right.left.right);
//        System.out.println(tree.tree.right.left.left);
        StdDraw.setPenRadius(.02);
        tree.draw();
//        for (Point2D p : tree.tree) {
//            System.out.println(p);
//        }
        
    }
}