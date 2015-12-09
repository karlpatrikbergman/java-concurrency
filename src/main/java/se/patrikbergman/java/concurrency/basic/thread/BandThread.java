package se.patrikbergman.java.concurrency.basic.thread;

/**
 * Runnable or Thread?
 * Which of these idioms should you use? The first idiom, which employs a Runnable object, is more
 * general, because the Runnable object can subclass a class other than Thread. The second idiom is easier to use in
 * simple applications, but is limited by the fact that your task class must be a descendant of Thread.
 *
 * When you call bandThread.start(), it starts a new thread and calls the run() method of bandThread internally to
 * execute it within that new thread.
 */
class BandThread extends Thread {

    /**
     * Thread implements Runnable but has an "empty" implementation of run()
     */
    @Override
    public void run() {
        System.out.printf("%s is running!", BandThread.class.getSimpleName());
    }

    public static void main(String args[]) {
        BandThread bandThread = new BandThread();
        bandThread.start();
    }

}
