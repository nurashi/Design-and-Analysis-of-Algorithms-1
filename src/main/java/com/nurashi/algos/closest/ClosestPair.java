package com.nurashi.algos.closest;

import java.util.Arrays;
import java.util.Comparator;

import com.nurashi.algos.util.metrics.Metrics;

public class ClosestPair {

    public static double findClosest(Point[] points, Metrics metrics) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least two points required");
        }

        Point[] sortedByX = points.clone();
        metrics.incAllocations(); // Allocation for cloned array
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] aux = new Point[points.length];
        metrics.incAllocations(); // Allocation for auxiliary array
        return closestRecursive(sortedByX, aux, 0, points.length - 1, metrics);
    }

    private static double closestRecursive(Point[] pts, Point[] aux, int left, int right, Metrics metrics) {
        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    metrics.incComparisons(); // Distance comparison
                    min = Math.min(min, Point.distance(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            metrics.incAllocations(); // Allocation for sorting
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;

        double dl = closestRecursive(pts, aux, left, mid, metrics);
        double dr = closestRecursive(pts, aux, mid + 1, right, metrics);
        metrics.incComparisons(); // Comparison: dl vs dr
        double d = Math.min(dl, dr);

        mergeByY(pts, aux, left, mid, right, metrics);

        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            metrics.incComparisons(); // Comparison: Math.abs(pts[i].x - midX) < d
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripSize++] = pts[i];
            }
        }

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                metrics.incComparisons(); // Distance comparison
                metrics.incComparisons(); // Y-coordinate comparison
                d = Math.min(d, Point.distance(aux[i], aux[j]));
            }
        }
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incComparisons(); // Comparison: pts[i].y <= pts[j].y
            if (pts[i].y <= pts[j].y) {
                aux[k++] = pts[i++];
            } else {
                aux[k++] = pts[j++];
            }
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        for (i = left; i <= right; i++) pts[i] = aux[i];
    }
}
