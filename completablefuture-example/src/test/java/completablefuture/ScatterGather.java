package completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ScatterGather {

    private static final TaskGenerator taskGenerator = new TaskGenerator();


    public static void sequential() {
        long lStartTime = Instant.now().toEpochMilli();

        List<String> gatheredSequentialResult = IntStream.range(0, 5)
                .boxed()
                .map(taskGenerator::generateTask)
                .collect(Collectors.toList());
        gatheredSequentialResult.forEach(log::info);

        long lEndTime = Instant.now().toEpochMilli();
        long output = lEndTime - lStartTime;
        log.info("Elapsed time in milliseconds: " + output);
    }


    public static void concurrent() {

        long lStartTime = Instant.now().toEpochMilli();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<CompletableFuture<String>> gatheredFutureResult = IntStream.range(0, 5)
                .boxed()
                .map(i -> taskGenerator.generateTask(i, executorService))
                .collect(Collectors.toList());

        CompletableFuture<List<String>> result = CompletableFuture.allOf(gatheredFutureResult.toArray(new CompletableFuture[gatheredFutureResult.size()]))
                .thenApply(v -> gatheredFutureResult.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        result.thenAccept(l -> l.forEach(log::info));

        executorService.shutdown();
        while(!executorService.isTerminated()) {

        }

        long lEndTime = Instant.now().toEpochMilli();
        long output = lEndTime - lStartTime;
        log.info("Elapsed time in milliseconds: " + output);
    }

    public static void main(String args[]) {
        sequential();
        log.info("*****");
        concurrent();
    }
}
