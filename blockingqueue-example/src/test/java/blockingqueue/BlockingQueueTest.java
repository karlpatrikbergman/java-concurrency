package blockingqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class BlockingQueueTest {
    private Producer producer;
    private Consumer consumer;
    private final int startInclusive = 0; //The (inclusive) initial value
    private final int endExclusive = 3; //The exclusive upper bound
    private AtomicInteger produceCounter = new AtomicInteger(0);
    private AtomicInteger consumeCounter = new AtomicInteger(0);

    @Before
    public void setup() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1024);
        producer = new Producer(blockingQueue);
        consumer = new Consumer(blockingQueue);
    }

    @Test
    public void test() throws InterruptedException {
        Runnable produceTask = () -> IntStream.range(startInclusive, endExclusive)
                .forEach(i -> {
                    try {
                        String item = "Item " + i;
                        producer.add(item);
                        produceCounter.incrementAndGet();
                        System.out.printf("Producer %s in thread %d produced item %s%n", producer, Thread.currentThread().getId(), item);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        Runnable consumeTask = () -> IntStream.range(startInclusive, endExclusive)
                .forEach(i -> {
                    String item = null;
                    try {
                        item = consumer.take();
                        consumeCounter.incrementAndGet();
                        System.out.printf("Consumer %s in thread %d consumed item %s%n", consumer, Thread.currentThread().getId(), item);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });

        Thread thread1 = new Thread(produceTask);
        Thread thread2 = new Thread(consumeTask);
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        assertEquals(3, produceCounter.get());
        assertEquals(3, consumeCounter.get());
    }
}
