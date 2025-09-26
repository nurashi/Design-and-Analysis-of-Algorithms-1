package com.nurashi.algos.util.array;

import java.util.Random;

public class ArrayUtils {
    private static final Random RAND = new Random();

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static int partition(int[] arr, int low, int high, com.nurashi.algos.util.metrics.Metrics metrics) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            metrics.incComparisons(); // Comparison: arr[j] <= pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RAND.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static void requireNonEmpty(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
    }
}
