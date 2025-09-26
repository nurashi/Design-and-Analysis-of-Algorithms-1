package com.nurashi.algos.select;

import java.util.Arrays;

import com.nurashi.algos.util.array.ArrayUtils;
import com.nurashi.algos.util.metrics.Metrics;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        ArrayUtils.requireNonEmpty(arr);
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return momSelect(arr, 0, arr.length - 1, k, metrics);
    }

    private static int momSelect(int[] arr, int left, int right, int k, Metrics metrics) {
        if (left == right) {
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right, metrics);

        int pivotIndex = partitionWithPivot(arr, left, right, pivot, metrics);

        metrics.incComparisons(); // Comparison: k == pivotIndex
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            metrics.incComparisons(); // Comparison: k < pivotIndex
            return momSelect(arr, left, pivotIndex - 1, k, metrics);
        } else {
            return momSelect(arr, pivotIndex + 1, right, k, metrics);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;

        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            metrics.incAllocations(); // Allocation for sorting small array
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5.0);
        int[] medians = new int[numMedians];
        metrics.incAllocations(); // Allocation for medians array

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            metrics.incAllocations(); // Allocation for sorting sub-array
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            medians[i] = arr[medianIndex];
        }

        return medianOfMedians(medians, 0, medians.length - 1, metrics);
    }

   
    private static int partitionWithPivot(int[] arr, int left, int right, int pivotValue, Metrics metrics) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            metrics.incComparisons(); // Comparison: arr[i] == pivotValue
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        ArrayUtils.swap(arr, pivotIndex, right);

        return ArrayUtils.partition(arr, left, right, metrics);
    }
}