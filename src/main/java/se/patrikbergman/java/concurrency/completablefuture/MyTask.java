package se.patrikbergman.java.concurrency.completablefuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class MyTask {

    String perform(int i) {
        int randomNumber = new Random().nextInt(5);
        sleep(randomNumber);
        return "Sleep time " + randomNumber + " seconds";
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


//    CompletableFuture<String> generateTask(int i, ExecutorService executorService) {
//        return CompletableFuture.supplyAsync(() -> {
//            sleep();
//            if (i == 5) {
//                throw new RuntimeException("Run, it is a 5!");
//            }
//            return i + "-" + "test";
//        }, executorService);
//    }
