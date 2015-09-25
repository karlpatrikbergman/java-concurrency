package se.patrikbergman.java.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

class MyTask {

    String perform(int i) {
        sleep();
        return Integer.toString(i);
    }

    CompletableFuture<String> generateTask(int i, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            if (i == 5) {
                throw new RuntimeException("Run, it is a 5!");
            }
            return i + "-" + "test";
        }, executorService);
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
