package com.nurashi.algos.sort;

import java.util.Random;

import com.nurashi.algos.util.array.ArrayUtils;
import com.nurashi.algos.util.metrics.Metrics;


public class QuickSort {

    private static final Random RAND = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        ArrayUtils.requireNonEmpty(arr);
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int low, int high, Metrics metrics) {
        while (low < high) {
            int pivotIndex = low + RAND.nextInt(high - low + 1);
            ArrayUtils.swap(arr, pivotIndex, high);
            metrics.incAllocations();

            int p = partition(arr, low, high, metrics);

            if (p - low < high - p) {
                quickSort(arr, low, p - 1, metrics);
                low = p + 1;
            } else {
                quickSort(arr, p + 1, high, metrics);
                high = p - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, Metrics metrics) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                ArrayUtils.swap(arr, i, j);
                metrics.incAllocations();
            }
        }
        ArrayUtils.swap(arr, i + 1, high);
        metrics.incAllocations();

        return i + 1;
    }
}