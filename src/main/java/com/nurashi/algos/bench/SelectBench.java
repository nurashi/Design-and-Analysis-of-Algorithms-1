package com.nurashi.algos.bench;

import com.nurashi.algos.select.DeterministicSelect;
import com.nurashi.algos.util.metrics.Metrics;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectBench {

    @Param({"1000", "10000", "100000"})
    private int n;

    private int[] arr;
    private int k;

    @Setup(Level.Invocation)
    public void setup() {
        arr = new Random(42).ints(n, 0, n * 10).toArray();
        k = n / 2; 
    }

    @Benchmark
    public int benchmarkSelect() {
        Metrics metrics = new Metrics();
        return DeterministicSelect.select(Arrays.copyOf(arr, arr.length), k, metrics);
    }

    @Benchmark
    public int benchmarkSortAndPick() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[k];
    }
}
