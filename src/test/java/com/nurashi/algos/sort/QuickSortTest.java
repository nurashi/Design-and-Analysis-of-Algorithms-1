package com.nurashi.algos.sort;
import org.junit.jupiter.api.Test;

import com.nurashi.algos.util.metrics.Metrics;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 1, 3, 3, 2, 5, 1};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 5, 5}, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = {10, -3, 5, 0, 7, 2};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{-3, 0, 2, 5, 7, 10}, arr);
    }
}
