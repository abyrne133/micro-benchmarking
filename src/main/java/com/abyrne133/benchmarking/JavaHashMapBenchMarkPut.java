package com.abyrne133.benchmarking;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@Warmup(iterations = 5, batchSize = 10_000)
@Measurement(iterations = 5, batchSize = 1_000)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(2)
public class JavaHashMapBenchMarkPut {

    // power of 2 and power of 10 initial capacities.
    @Param({"16", "100", "1024", "10000", "65536", "100000", "524288", "1000000", "1048576"})
    Integer initialCapacity;

    // fractions of 8
    @Param({"0.125", "0.250", "0.375", "0.5", "0.625", "0.75", "0.875", "1"})
    Float loadFactor;

    HashMap<Integer, Object> hashMap;
    Random random = new Random(376832);
    int key;

    @Setup(Level.Iteration)
    public void initHashMap() {
        hashMap = new HashMap<>(initialCapacity, loadFactor);
    }

    @Setup(Level.Invocation)
    public void incrementKeyValue() {
        key = random.nextInt();
    }

    @Benchmark
    public Object put() {
        return hashMap.put(key, new Object());
    }

    @Benchmark
    public Object putTerribleHashCode() {
        return hashMap.put(key, new TerribleHashCode());
    }

    @Benchmark
    public Object putPoorHashCode() {
        return hashMap.put(key, new PoorHashCode(key, initialCapacity));
    }

    private static class TerribleHashCode {
        @Override
        public int hashCode() {
            return 1;
        }
    }

    private static class PoorHashCode {

        private final int key;
        private final int tableSize;

        private PoorHashCode(int key, int tableSize) {
            this.key = key;
            this.tableSize = tableSize;
        }

        @Override
        public int hashCode() {
            return key % (tableSize / 5); //only uses one fifth of the table buckets
        }
    }
}
