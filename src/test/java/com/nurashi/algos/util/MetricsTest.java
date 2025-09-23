package com.nurashi.algos.util;

import org.junit.jupiter.api.Test;

import com.nurashi.algos.util.metrics.Metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetricsTest {

    @Test
    void testComparisons() {
        Metrics m = new Metrics();
        m.incComparisons();
        m.addComparisons(4);
        assertEquals(2, m.getComparisons());
    }

    @Test
    void testAllocations() {
        Metrics m = new Metrics();
        m.incAllocations();
        m.incAllocations();
        assertEquals(2, m.getAllocations());
    }

    @Test
    void testRecursionDepth() {
        Metrics m = new Metrics();
        m.setMaxRecursionDepth(1);
        m.setMaxRecursionDepth(3);
        m.setMaxRecursionDepth(2);
        assertEquals(3, m.getMaxRecursionDepth());
    }
}
