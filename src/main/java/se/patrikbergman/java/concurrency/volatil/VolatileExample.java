package se.patrikbergman.java.concurrency.volatil;


public class VolatileExample {

    private static volatile int nrOfReleasedRecords = 0;

    static class MinorThreatFan extends Thread {
        private int nrOfPurchasedRecords = 0;

        @Override
        public void run() {
            while(true) {
                if(nrOfPurchasedRecords < nrOfReleasedRecords) {
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


    public static void main(String[] args) {
        new MinorThreatFan().start();
        new MinorThreat().start();
    }

}
