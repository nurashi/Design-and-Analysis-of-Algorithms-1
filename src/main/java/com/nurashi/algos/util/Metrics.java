package com.nurashi.algos.util;

public class Metrics {

    private long comparisons;
    private long allocations;
    private int maxRecursionDepth;

    public void incComparisons() {
        comparisons++;
    }
    public void addComparisons(int i) {
        comparisons++;
    }

    public void incAllocations() {
        allocations++;
    }

    public void setMaxRecursionDepth(int depth) {
        if (depth > maxRecursionDepth) {
            maxRecursionDepth = depth;
        }

    }
    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxRecursionDepth = 0;
    }
}
