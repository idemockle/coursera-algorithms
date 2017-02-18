import java.util.Arrays;

public class SegDefArray {
    private Point[] firstPoints;
    private Point[] lastPoints;
    private int size;
    
    public SegDefArray() {
        firstPoints = new Point[4];
        lastPoints = new Point[4];
    }
    
    public void add(Point first, Point last) {
        if (size == firstPoints.length) {
            Point[] newFirstPoints = Arrays.copyOf(firstPoints, firstPoints.length*2);
            Point[] newLastPoints = Arrays.copyOf(lastPoints, lastPoints.length*2);
            firstPoints = newFirstPoints;
            lastPoints = newLastPoints;
        }
        firstPoints[size] = first;
        lastPoints[size] = last;
        size++;
    }
    
    public Point getFirst(int i) {
        if (i >= size || i < 0) {
            throw new java.lang.ArrayIndexOutOfBoundsException();
        }
        return firstPoints[i];
    }
    
    public Point getLast(int i) {
        if (i >= size || i < 0) {
            throw new java.lang.ArrayIndexOutOfBoundsException();
        }
        return lastPoints[i];
    }
    
    public boolean isIn(Point first, Point last) {
        if (size == 0) return false;
        
        for (int i=0; i<size; i++) {
            if (first.compareTo(firstPoints[i]) == 0
             && last.compareTo(lastPoints[i]) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public int size() { return size; }
    
    public LineSegment[] toLineSegments() {
        LineSegment[] out = new LineSegment[size];
        for (int i=0; i<size; i++) {
            out[i] = new LineSegment(firstPoints[i],lastPoints[i]);
        }
        return out;
    }
}