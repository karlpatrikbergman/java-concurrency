package se.patrikbergman.java.concurrency.basic.thread;

/**
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
