package se.patrikbergman.java.concurrency.runnable;

public class BandApplication {

    public static void main(String args[]) {
        BandMember john = new BandMember("John", 5000);
        BandMember paul = new BandMember("Paul", 100);

        john.setFellowBandMember(paul);
        paul.setFellowBandMember(john);

        john.start();
        paul.start();

    }
}
