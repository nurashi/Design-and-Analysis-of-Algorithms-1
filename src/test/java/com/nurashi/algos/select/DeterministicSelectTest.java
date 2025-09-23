package com.nurashi.algos.select;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {3, 1, 4, 2, 5};
        assertEquals(3, DeterministicSelect.select(arr, 2)); 
    }

    @Test
    void testMedianOdd() {
        int[] arr = {9, 2, 7, 1, 6, 3, 8};
        int medianIndex = arr.length / 2;
        int expected = Arrays.stream(arr).sorted().toArray()[medianIndex];
        assertEquals(expected, DeterministicSelect.select(arr, medianIndex));
    }

    @Test
    void testMedianEven() {
        int[] arr = {10, 40, 30, 20};
        int medianIndex = arr.length / 2;
        int expected = Arrays.stream(arr).sorted().toArray()[medianIndex];
        assertEquals(expected, DeterministicSelect.select(arr, medianIndex));
    }

    @Test
    void testKth() {
        int[] arr = {50, 20, 40, 10, 30};
        assertEquals(10, DeterministicSelect.select(arr, 0)); 
        assertEquals(50, DeterministicSelect.select(arr, arr.length - 1)); 
    }
}
