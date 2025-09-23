package com.nurashi.algos.sort;

import org.junit.jupiter.api.Test;

import com.nurashi.algos.util.metrics.Metrics;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
        assertTrue(m.getComparisons() > 0);
        assertTrue(m.getAllocations() > 0);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseOrder() {
        int[] arr = {9, 8, 7, 6, 5};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testLargeRandomArray() {
        Random rand = new Random(42);
        int[] arr = rand.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
        System.out.printf("Comparisons: %d, Assignments: %d, MaxDepth: %d%n", m.getComparisons(), m.getAllocations(), m.getMaxRecursionDepth());
    }
}
