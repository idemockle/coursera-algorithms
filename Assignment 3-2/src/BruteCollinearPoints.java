import java.util.Arrays;

public class BruteCollinearPoints {
    
    private Point[] points;
    
    public BruteCollinearPoints(Point[] points){
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
        int out = 0;
        double s1;
        double s2;
        double s3;
        
        for (int p = 0; p <= points.length-4; p++) {
            for (int q = p+1; q <= points.length-3; q++) {
                for (int r = q+1; r <= points.length-2; r++) {
                    for (int s = r+1; s <= points.length-1; s++) {
                        s1 = points[p].slopeTo(points[q]);
                        s2 = points[p].slopeTo(points[r]);
                        s3 = points[p].slopeTo(points[s]);
                        if (Double.compare(s1, s2) == 0 
                            && Double.compare(s1, s3) == 0) {
                            out++;
                        }
                    }
                }
            }
        }
        
        return out;
    }
    
    public LineSegment[] segments() {
        LineSegment[] out = new LineSegment[numberOfSegments()];
        int outIdx = 0;
        Point[] currPoints = new Point[4];
        double s1;
        double s2;
        double s3;
        
        for (int p = 0; p <= points.length-4; p++) {
            for (int q = p+1; q <= points.length-3; q++) {
                for (int r = q+1; r <= points.length-2; r++) {
                    for (int s = r+1; s <= points.length-1; s++) {
                        s1 = points[p].slopeTo(points[q]);
                        s2 = points[p].slopeTo(points[r]);
                        s3 = points[p].slopeTo(points[s]);
                        if (Double.compare(s1, s2) == 0 
                            && Double.compare(s1, s3) == 0) {
                            currPoints[0] = points[p];
                            currPoints[1] = points[q];
                            currPoints[2] = points[r];
                            currPoints[3] = points[s];
                            Arrays.sort(currPoints);
                            out[outIdx++] = new LineSegment(currPoints[0], currPoints[3]);
                        }
                    }
                }
            }
        }
        return out;
    }
    
    
}
