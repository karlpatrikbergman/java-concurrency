package se.patrikbergman.java.concurrency.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ScatterGather {

    @Test
    public void sequential() {
        List<String> gatheredResult = IntStream.range(0, 5)
                .boxed()
                .map(i -> new MyTask().perform(i))
                .collect(Collectors.toList());
        gatheredResult.forEach(log::info);
    }
}
