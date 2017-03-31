package basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Runnable or Thread?
 * Which of these idioms should you use? The first idiom, which employs a Runnable object, is more
 * general, because the Runnable object can subclass a class other than Thread. The second idiom is easier to use in
 * simple applications, but is limited by the fact that your task class must be a descendant of Thread.
 *
 * When you call bandThread.start(), it starts a new thread and calls the run() method of bandThread internally to
 * execute it within that new thread.
 */
@Slf4j
class MyThread extends Thread {

    /**
     * Thread implements Runnable but has an "empty" implementation of run()
     */
    @Override
    public void run() {
        log.info("In {}.run()", this.getClass().getSimpleName());
        long timeout = 5;
        log.info("Sleeping for {} seconds....", timeout);
        mySleep(timeout);
        log.info("Woke up!");
    }

    private void mySleep(long timemout) {
        try {
            TimeUnit.SECONDS.sleep(timemout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
