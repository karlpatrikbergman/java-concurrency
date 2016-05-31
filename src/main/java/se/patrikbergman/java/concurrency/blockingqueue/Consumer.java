package se.patrikbergman.java.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

class Consumer {
    private final BlockingQueue<String> blockingQueue;

    Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    String take() throws InterruptedException {
        return blockingQueue.take();
    }
}
