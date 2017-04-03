package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class TaskGenerator {

    String generateTask(int i) {
        int sleepTime = 2;
        sleep(sleepTime);
        return String.format("Task %d. Sleep time %d s", i, sleepTime);
    }

    CompletableFuture<String> generateTask(int i, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> generateTask(i), executorService);
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
