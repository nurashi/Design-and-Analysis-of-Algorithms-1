package com.nurashi.algos.sort;

import com.nurashi.algos.util.Metrics;

import java.util.Random;

public class QuickSort {

    private static final Random RAND = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int start, int end, Metrics metrics) {
        while (start < end) {
            // choose randomized pivot
            int pivotIndex = start + RAND.nextInt(end - start + 1);
            swap(arr, pivotIndex, end, metrics);

            int p = partition(arr, start, end, metrics);

            // recurse into smaller side first (bounded stack)
            if (p - start < end - p) {
                quickSort(arr, start, p - 1, metrics);
                start = p + 1; // iterate into larger half
            } else {
                quickSort(arr, p + 1, end, metrics);
                end = p - 1; // iterate into larger half
            }
        }
    }

    private static int partition(int[] arr, int start, int end, Metrics metrics) {
        int pivot = arr[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, metrics);
            }
        }
        swap(arr, i + 1, end, metrics);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            metrics.incAllocations(); 
        }
    }
}
