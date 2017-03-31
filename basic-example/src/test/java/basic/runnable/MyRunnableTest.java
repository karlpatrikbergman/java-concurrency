package basic.runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * Normally, the JVM will terminate when the last non-daemon thread terminates.
 * You might expect that simply calling t.setDaemon(false) on the thread would prevent the
 * JVM from exiting before the task is finished. However, junit will call System.exit()
 * when the main thread has finished.
 * http://stackoverflow.com/questions/16616590/thread-behaving-strangely-in-junit
 */

@Slf4j
public class MyRunnableTest {

    public static void main(String args[]) {
        log.info("In {}.main()", MyRunnableTest.class.getSimpleName());
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();
        log.info("Now we are done in main thread");
    }
}
