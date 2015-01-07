package se.patrikbergman.java.concurrency.volatil;

/**
 * http://java.dzone.com/articles/java-volatile-keyword-0:
 * So what happens? Each thread has its own stack, and so its own copy of variables it can access.
 * When the thread is created, it copies the value of all accessible variables in its own memory.
 * The volatile keyword is used to say to the jvm "Warning, this variable may be modified in an other Thread".
 * Without this keyword the JVM is free to make some optimizations, like never refreshing those local copies in
 * some threads. The volatile force the thread to update the original variable for each variable. The volatile
 * keyword could be used on every kind of variable, either primitive or objects!
 */
public class HardcorePunkRealm {

    static class MinorThreatFan extends Thread {
        private int nrOfPurchasedRecords = 0;

        @Override
        public void run() {
            while(true) {
                if(nrOfPurchasedRecords < MinorThreat.nrOfReleasedRecords) {
                    buyLatestRecord();
                }
            }
        }

        private void buyLatestRecord() {
            nrOfPurchasedRecords++;
            System.out.println("Fan bought Minor Threat release nr " + nrOfPurchasedRecords);
        }

    }

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
            System.out.println("Minor Thread released record nr " + nrOfReleasedRecords);
        }

        private void goOnTour() {
            try {
                sleep(2000);
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
    }

}
