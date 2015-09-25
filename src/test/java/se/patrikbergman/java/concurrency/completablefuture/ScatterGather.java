package se.patrikbergman.java.concurrency.completablefuture;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScatterGather {

    @Test
    public void test() {
        List<String> strings =
                IntStream.range(0, 5)
                .boxed()
                .map(i -> new MyTask().perform(i))
                .collect(Collectors.toList());
        System.out.println(strings);
    }
}
