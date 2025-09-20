package com.nurashi.algos.sort;

import com.nurashi.algos.util.Metrics;


public class MergeSort { 
    
    private static final int CUTOFF = 10;
    
    public static void sort(int[] arr, Metrics metrics) {
        // Create auxiliary array for merging
        int[] aux = new int[arr.length];

        metrics.incAllocations();

        // Start the recursive sorting
        mergeSort(arr, aux, 0, arr.length - 1, metrics);
    }

    private static void mergeSort(int[] arr, int[] aux, int start, int end, Metrics metrics) {
        // Base case: if left >= right, array has 0 or 1 element, so it's already sorted
         if (end - start <= CUTOFF) {
            insertionSort(arr, start, end, metrics);
            return;
        }
        
         // Divide: find the middle point
        int mid = start + (end - start) / 2;
        
        // Conquer: recursively sort left and right halves
        mergeSort(arr, aux, start, mid, metrics);
        mergeSort(arr, aux, mid + 1, end, metrics);
        
        // Combine: merge the two sorted halves
        merge(arr, aux, start, mid, end, metrics);
    }

    private static void merge(int[] arr, int[] aux, int start, int mid, int end, Metrics metrics) {
        // Copy data to auxiliary array
        for (int i = start; i <= end; i++) {
            aux[i] = arr[i];
        }

        // Merge the two halves back into arr
        int i = start;      // Index for left subarray
        int j = mid + 1;   // Index for right subarray
        int k = start;      // Index for merged array

        // Compare elements from both halves and merge in sorted order
        while (i <= mid && j <= end) {
            metrics.incComparisons();
            if (aux[i] <= aux[j]) {
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from left half (if any)
        while (i <= mid) {
            arr[k] = aux[i];
            i++;
            k++;
        }

        // Copy remaining elements from right half (if any)
        while (j <= end) {
            arr[k] = aux[j];
            j++;
            k++;
        }
    }


    private static void insertionSort(int[] arr, int start, int end, Metrics metrics) {
        for (int i = start + 1; i <= end; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= start) {
                metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}


