package se.patrikbergman.java.concurrency.basic.runnable;

/**
 * When you call bandThread.start(), it starts a new thread and calls the run() method of bandThread internally to
 * execute it within that new thread.
 */
class BandRunnable implements Runnable {

    @Override
    public void run() {
        System.out.printf("%s is running!", BandRunnable.class.getSimpleName());
    }

    public static void main(String args[]) {
        Thread bandThread = new Thread(new BandRunnable());
        bandThread.start();
    }
}
