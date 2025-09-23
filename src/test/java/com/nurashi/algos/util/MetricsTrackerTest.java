package com.nurashi.algos.util;

import org.junit.jupiter.api.Test;

import com.nurashi.algos.util.metrics.Metrics;
import com.nurashi.algos.util.metrics.MetricsTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetricsTrackerTest {

    @Test
    void testRecursionTracking() {
        Metrics m = new Metrics();
        MetricsTracker tracker = new MetricsTracker(m);

        tracker.enterRecursion(); 
        tracker.enterRecursion(); 
        tracker.exitRecursion();  
        tracker.exitRecursion();  

        assertEquals(2, m.getMaxRecursionDepth());
    }
}

