package blockingqueue;

import java.util.concurrent.BlockingQueue;

class Producer {
    private final BlockingQueue<String> blockingQueue;

    Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    void add(String item) throws InterruptedException {
        blockingQueue.put(item);
    }
}
