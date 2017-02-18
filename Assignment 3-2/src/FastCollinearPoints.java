import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        
        for (Point point : points) {
            if (point == null) throw new NullPointerException();
        }
        
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        
        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(this.points);
    }
    
    public int numberOfSegments() {
        return segments().length;
    }
    
    public LineSegment[] segments() {
        SegDefArray segDefs = new SegDefArray();
        Point[] aux = Arrays.copyOf(points, points.length);
        Point p_i;
        Point[] currPoints = null;
        int sameSlopeStart;
        
        for (int i = 0; i < points.length; i++) {
            p_i = points[i];
            Arrays.sort(aux, p_i.slopeOrder());
//            System.out.println(Arrays.toString(aux));
            sameSlopeStart = 1;
            double lastSlope = p_i.slopeTo(aux[1]);
            double currSlope;
            for (int j = 2; j < aux.length; j++) {
                currSlope = p_i.slopeTo(aux[j]);
                if (Double.compare(lastSlope, currSlope) != 0 
                 || (j == aux.length - 1 && Double.compare(lastSlope, currSlope) == 0)) {
                    if (j == aux.length - 1 && Double.compare(lastSlope, currSlope) == 0) j++;
                    if (j - sameSlopeStart >= 3) {
                        currPoints = new Point[j - sameSlopeStart + 1];
                        currPoints[0] = p_i;
                        for (int k = 1, l = sameSlopeStart; k < currPoints.length; k++, l++) {
                            currPoints[k] = aux[l];
                        }
//                        System.out.println("Presort: "+Arrays.toString(currPoints));
                        Arrays.sort(currPoints);
//                        System.out.println("Postsort: "+Arrays.toString(currPoints));
                        if (!segDefs.isIn(currPoints[0], currPoints[currPoints.length-1])) {
                            segDefs.add(currPoints[0], currPoints[currPoints.length-1]);
                        }
                    }
                    sameSlopeStart = j;
                }
                lastSlope = currSlope;
            }
        }
        return segDefs.toLineSegments();
    }
    
    private class SegDefArray {
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
}
