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
                    aux[0] = null;
                    for (int j = 1; j <= sameSlopeCounter; j++) {
                        aux[i-j] = null;
                    }
                    sameSlopeCounter = 1;
                } else {
                    sameSlopeCounter = 1;
                }
            }
            int numNull = 0;
            for (Point a : aux) {
                if (a == null) {
                    numNull++;
                }
            }
            Point[] newAux = new Point[aux.length-numNull];
            int newAuxIdx = 0;
            for (Point a : aux) {
                if (a != null) {
                    newAux[newAuxIdx++] = a;
                }
            }
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
                    aux[0] = null;
                    for (int j = 1; j <= sameSlopeCounter; j++) {
                        collinPoints[j] = aux[i-j];
                        aux[i-j] = null;
                    }
                    java.util.Arrays.sort(collinPoints);
                    segmentsOut[segIdx] = new LineSegment(collinPoints[0], collinPoints[collinPoints.length-1]);
                    segIdx++;
                    sameSlopeCounter = 1;
                } else {
                    sameSlopeCounter = 1;
                }
            }
            int numNull = 0;
            for (Point a : aux) {
                if (a == null) {
                    numNull++;
                }
            }
            Point[] newAux = new Point[aux.length-numNull];
            int newAuxIdx = 0;
            for (int a = 0; a<aux.length; a++) {
                if (aux[a] != null) {
                    newAux[newAuxIdx++] = aux[a];
                }
            }
            aux = newAux;
        }
        return segmentsOut;
    }
}
