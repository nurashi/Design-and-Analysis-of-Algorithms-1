package com.nurashi.algos.closest;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least two points required");
        }

        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] aux = new Point[points.length];
        return closestRecursive(sortedByX, aux, 0, points.length - 1);
    }

    private static double closestRecursive(Point[] pts, Point[] aux, int left, int right) {
        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, Point.distance(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;

        double dl = closestRecursive(pts, aux, left, mid);
        double dr = closestRecursive(pts, aux, mid + 1, right);
        double d = Math.min(dl, dr);

        mergeByY(pts, aux, left, mid, right);

        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripSize++] = pts[i];
            }
        }

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                d = Math.min(d, Point.distance(aux[i], aux[j]));
            }
        }
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
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
