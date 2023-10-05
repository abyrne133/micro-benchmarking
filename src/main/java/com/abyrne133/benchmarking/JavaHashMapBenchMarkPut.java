package com.abyrne133.benchmarking;

import org.openjdk.jmh.annotations.*;

import java.util.AbstractMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime, Mode.SingleShotTime})
@Warmup(iterations = 5, batchSize = 10_000)
@Measurement(iterations = 10, batchSize = 10_000)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
public class JavaHashMapBenchMarkPut {

    @Param({"default-constructor", "control", "capacity", "half-capacity", "capacity-no-load-factor", "custom-default", "custom-capacity", "custom-terrible-hash", "custom-bad-hash", "custom-basic-hash", "custom-hash-no-modulo"})
    String hashMapType;

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    AbstractMap<Integer, Integer> hashMap;

    Random random = new Random(376832);
    int key;

    @Setup(Level.Iteration)
    public void initHashMap() {
        switch (hashMapType) {
            case "default-constructor":
                hashMap = new java.util.HashMap<>();
                break;
            case "control":
                hashMap = new java.util.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
                break;
            case "capacity":
                hashMap = new java.util.HashMap<>(10_001, DEFAULT_LOAD_FACTOR);
                break;
            case "half-capacity":
                hashMap = new java.util.HashMap<>(500, DEFAULT_LOAD_FACTOR);
                break;
            case "capacity-no-load-factor":
                hashMap = new java.util.HashMap<>(10_001, 1f);
                break;
            case "custom-default":
                hashMap = new com.abyrne.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
                break;
            case "custom-capacity":
                hashMap = new com.abyrne.HashMap<>(10_001, DEFAULT_LOAD_FACTOR);
                break;
            case "custom-terrible-hash":
                hashMap = new com.abyrne.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, (k) -> 1);
                break;
            case "custom-bad-hash":
                hashMap = new com.abyrne.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, (k) -> k.hashCode() % 6);
                break;
            case "custom-basic-hash":
                hashMap = new com.abyrne.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, Object::hashCode);
                break;
            case "custom-hash-no-modulo":
                hashMap = new com.abyrne.HashMap<>(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, (k) -> {
                    int h;
                    return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
                });
                break;
        }
    }

    @Setup(Level.Invocation)
    public void incrementKeyValue() {
        key = random.nextInt();
    }

    @Benchmark
    public Integer put() {
        return hashMap.put(key, key);
    }
}
