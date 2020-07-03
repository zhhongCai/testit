package com.test.it.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019/12/7 15:45
 * @Description:
 */
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Thread)
public class StringAndStringBuilderbenchmark {

    @Benchmark
    public void stringAdd() {
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
//        System.out.println(str);
    }

    @Benchmark
    public void stringBuilderAdd() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            str.append(i);
        }
//        System.out.println(str);
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(StringAndStringBuilderbenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(2)
                .build();
        new Runner(opt).run();
    }

}
