package com.nurashi.algos.select;

import java.util.Arrays;

import com.nurashi.algos.util.array.ArrayUtils;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        ArrayUtils.requireNonEmpty(arr);
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return momSelect(arr, 0, arr.length - 1, k);
    }

    private static int momSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);

        int pivotIndex = partitionWithPivot(arr, left, right, pivot);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return momSelect(arr, left, pivotIndex - 1, k);
        } else {
            return momSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;

        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5.0);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            medians[i] = arr[medianIndex];
        }

        return medianOfMedians(medians, 0, medians.length - 1);
    }

   
    private static int partitionWithPivot(int[] arr, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        ArrayUtils.swap(arr, pivotIndex, right);

        return ArrayUtils.partition(arr, left, right);
    }
}