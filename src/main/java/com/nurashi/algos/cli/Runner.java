package com.nurashi.algos.cli;

import com.nurashi.algos.util.metrics.Metrics;
import com.nurashi.algos.sort.MergeSort;
import com.nurashi.algos.sort.QuickSort;
import com.nurashi.algos.select.DeterministicSelect;
import com.nurashi.algos.closest.Point;
import com.nurashi.algos.closest.ClosestPair;

import java.util.Random;

public class Runner {

    public static void printHeader() {
        System.out.println("algorithmName;runTime;counter;depth;allocation");
    }

    public static void run(String[] args) {
        String algo = args[0].toLowerCase();
        int n = Integer.parseInt(args[1]);
        int k = (algo.equals("select") && args.length > 2) ? Integer.parseInt(args[2]) : -1;

        Random rand = new Random(42);
        Metrics metrics = new Metrics();

        long start, end;

        switch (algo) {
            case "mergesort" -> {
                int[] arr = rand.ints(n, 0, 1_000_000).toArray();
                start = System.nanoTime();
                MergeSort.sort(arr, metrics);
                end = System.nanoTime();
            }
            case "quicksort" -> {
                int[] arr = rand.ints(n, 0, 1_000_000).toArray();
                start = System.nanoTime();
                QuickSort.sort(arr, metrics);
                end = System.nanoTime();
            }
            case "select" -> {
                int[] arr = rand.ints(n, 0, 1_000_000).toArray();
                start = System.nanoTime();
                DeterministicSelect.select(arr, k, metrics);
                end = System.nanoTime();
            }
            case "closest" -> {
                Point[] pts = new Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
                }
                start = System.nanoTime();
                ClosestPair.findClosest(pts, metrics);
                end = System.nanoTime();
            }
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algo);
        }

        long durationMs = (end - start) / 1_000_000;

        System.out.printf("%s;%d;%d;%d;%d%n",
                algo, 
                durationMs, 
                metrics.getComparisons(), 
                metrics.getMaxRecursionDepth(), 
                metrics.getAllocations());
    }
}
