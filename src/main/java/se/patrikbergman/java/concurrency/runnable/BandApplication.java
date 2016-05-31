package se.patrikbergman.java.concurrency.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BandApplication {
    final static Logger log = LoggerFactory.getLogger(BandMember.class.getSimpleName());

    public static void main(String args[]) {
        BandMember john = new BandMember("John");
        BandMember paul = new BandMember("Paul");

        john.setFellowBandMember(paul);
        paul.setFellowBandMember(john);

        new Thread(john::addressFellowBandMember).start();

        new Thread(paul::addressFellowBandMember).start();
    }
}
