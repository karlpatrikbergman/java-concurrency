package basic.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThreadTest {

    public static void main(String args[]) {
        log.info("In {}.main()", MyThreadTest.class.getSimpleName());
        MyThread myThread = new MyThread();
        myThread.start();
        log.info("Now we are done in main thread");
    }
}
