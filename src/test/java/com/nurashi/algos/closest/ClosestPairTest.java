package com.nurashi.algos.closest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class ClosestPairTest {

    private static double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, Point.distance(points[i], points[j]));
            }
        }
        return min;
    }

    @Test
    void testSimpleCase() {
        Point[] pts = { new Point(0, 0), new Point(3, 4), new Point(7, 1) };
        assertEquals(5.0, ClosestPair.findClosest(pts), 1e-9);
    }

    @Test
    void testBruteComparisonSmall() {
        Random rand = new Random(42);
        Point[] pts = new Point[10];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(rand.nextInt(100), rand.nextInt(100));
        }
        double expected = bruteForce(pts);
        double actual = ClosestPair.findClosest(pts);
        assertEquals(expected, actual, 1e-9);
    }

    @Test
    void testBruteComparisonMedium() {
        Random rand = new Random(123);
        Point[] pts = new Point[200];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        double expected = bruteForce(pts);
        double actual = ClosestPair.findClosest(pts);
        assertEquals(expected, actual, 1e-9);
    }
}
