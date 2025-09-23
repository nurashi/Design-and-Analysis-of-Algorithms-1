package com.nurashi.algos.util.metrics;

public class MetricsTracker {
    private final Metrics metrics;
    private int currentDepth = 0;

    public MetricsTracker(Metrics metrics) {
        this.metrics = metrics;
    }

    public void enterRecursion() {
        currentDepth++;
        metrics.setMaxRecursionDepth(currentDepth);
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public Metrics getMetrics() {
        return metrics;
    }
}
