/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ian
 */
public class BruteCollinearPoints {
    private final Point[] points;
    
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
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
            
    public int numberOfSegments() {
        int countsegs = 0;
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p+1; q < points.length - 2; q++) {
                for (int r = q+1; r < points.length - 1; r++) {
                    for (int s = r+1; s < points.length; s++){
                        if (Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[r])) == 0 &&
                            Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[s])) == 0) {
                            countsegs++;
                        }
                    }
                }
            }
        }
        return countsegs;
    }

    public LineSegment[] segments() {
        LineSegment[] segmentsList = new LineSegment[numberOfSegments()];
        int idx = 0;
        Point[] currentPoints = new Point[4];
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p+1; q < points.length - 2; q++) {
                for (int r = q+1; r < points.length - 1; r++) {
                    for (int s = r+1; s < points.length; s++){
                        if (Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[r])) == 0 &&
                            Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[s])) == 0) {
                            currentPoints[0] = points[p];
                            currentPoints[1] = points[q];
                            currentPoints[2] = points[r];
                            currentPoints[3] = points[s];
                            java.util.Arrays.sort(currentPoints);
                            segmentsList[idx] = new LineSegment(currentPoints[0],currentPoints[3]);
                            idx++;
                        }
                    }
                }
            }
        }
        return segmentsList;
    }
}
