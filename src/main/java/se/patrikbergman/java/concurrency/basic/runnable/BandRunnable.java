package se.patrikbergman.java.concurrency.basic.runnable;

/**
 * Runnable or Thread?
 * Which of these idioms should you use? The first idiom, which employs a Runnable object, is more
 * general, because the Runnable object can subclass a class other than Thread. The second idiom is easier to use in
 * simple applications, but is limited by the fact that your task class must be a descendant of Thread.
 *
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
