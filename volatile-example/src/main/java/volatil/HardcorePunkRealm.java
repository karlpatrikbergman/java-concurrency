package volatil;

import lombok.extern.slf4j.Slf4j;

/**
 * So what happens?
 * Each thread has its own stack, and so its own copy of variables it can access.
 * When the thread is created, it copies the value of all accessible variables in its own memory.
 * The volatile keyword is used to say to the jvm "Warning, this variable may be modified in an other Thread".
 * Without this keyword the JVM is free to make some optimizations, like never refreshing those local copies in
 * some threads.
 * The volatile force the thread to update the original variable for each variable.
 * The volatile keyword could be used on every kind of variable, either primitive or objects!
 * http://java.dzone.com/articles/java-volatile-keyword-0:
 *
 * Try running this program with and without volatile MinorThreat.nrOfReleasedRecords!
 */
@Slf4j
class HardcorePunkRealm {

    @Slf4j
    static class MinorThreatFan extends Thread {
        private int nrOfPurchasedRecords = 0;

        @Override
        public void run() {
            while(nrOfPurchasedRecords < 5) {
                if(nrOfPurchasedRecords < MinorThreat.nrOfReleasedRecords) {
                    buyLatestRecord();
                }
            }
        }

        private void buyLatestRecord() {
            nrOfPurchasedRecords++;
            log.info("Fan bought Minor Threat release nr {}", nrOfPurchasedRecords);
        }

    }

    @Slf4j
    static class MinorThreat extends Thread {
        static volatile int nrOfReleasedRecords = 0;

        @Override
        public void run() {
            while(nrOfReleasedRecords < 5) {
                releaseNewRecord();
                goOnTour();
            }
        }

        private void releaseNewRecord() {
            nrOfReleasedRecords++;
            log.info("Minor Threat released record nr {}", nrOfReleasedRecords);
            takeSomeTime(2000);

        }

        private void goOnTour() {
            log.info("Minor Threat goes on tour");
            takeSomeTime(2000);
        }

        private void takeSomeTime(long time) {
            try {
                sleep(time);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MinorThreatFan minorThreatFan = new MinorThreatFan();
        minorThreatFan.start();
        MinorThreat minorThreat = new MinorThreat();
        minorThreat.start();
        log.info("Now we are done in main thread");
    }
}
