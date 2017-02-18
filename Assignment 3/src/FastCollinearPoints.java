public class FastCollinearPoints {
    private Point[] points;
    
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new java.lang.NullPointerException();
        }
        for (Point p:points) {
            if (p == null) {
                throw new java.lang.NullPointerException();
            }
        }
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        this.points = java.util.Arrays.copyOf(points, points.length);
    }
    
    public int numberOfSegments(){
        Point[] aux = java.util.Arrays.copyOf(points, points.length);
        int sameSlopeCounter;
        int collinCounter = 0;
        double prevSlope;
        double currSlope;
        
        for (Point p : points) {
            java.util.Arrays.sort(aux, p.slopeOrder());
            currSlope = Double.NEGATIVE_INFINITY;
            sameSlopeCounter = 1;
            
            for (int i = 0; i < aux.length; i++) {
                prevSlope = currSlope;
                currSlope = p.slopeTo(aux[i]);
                if (Double.compare(currSlope, prevSlope) == 0) {
                    sameSlopeCounter++;
                } else if (sameSlopeCounter >= 3) {
                    collinCounter++;
                    sameSlopeCounter = 1;
                } else {
                    sameSlopeCounter = 1;
                }
            }
            Point[] newAux = java.util.Arrays.copyOfRange(aux, 1, aux.length);
            aux = newAux;
        }
        return collinCounter;
    }
    public LineSegment[] segments() {
        Point[] aux = java.util.Arrays.copyOf(points, points.length);
        LineSegment[] segmentsOut = new LineSegment[numberOfSegments()];
        int sameSlopeCounter;
        int segIdx = 0;
        double prevSlope;
        double currSlope;
        
        for (Point p : points) {
            java.util.Arrays.sort(aux, p.slopeOrder());
            currSlope = Double.NEGATIVE_INFINITY;
            sameSlopeCounter = 1;
            for (int i = 1; i < aux.length; i++) {
                prevSlope = currSlope;
                currSlope = p.slopeTo(aux[i]);
                if (Double.compare(currSlope, prevSlope) == 0) {
                    sameSlopeCounter++;
                } else if (sameSlopeCounter >= 3 && prevSlope != Double.NEGATIVE_INFINITY) {
                    Point[] collinPoints = new Point[sameSlopeCounter + 1];
                    collinPoints[0] = p;
                    java.util.Arrays.sort(collinPoints);
                    for (int j = 1; j <= sameSlopeCounter; j++) {
                        collinPoints[j] = aux[i-j];
                    }
                    segmentsOut[segIdx] = new LineSegment(collinPoints[0], collinPoints[collinPoints.length-1]);
                    segIdx++;
                    sameSlopeCounter = 1;
                } else {
                    sameSlopeCounter = 1;
                }
            }
            Point[] newAux = java.util.Arrays.copyOfRange(aux, 1, aux.length);
            aux = newAux;
        }
        return segmentsOut;
    }
}
