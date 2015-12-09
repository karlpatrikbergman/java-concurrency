package se.patrikbergman.java.concurrency.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BandMember {
    final Logger log = LoggerFactory.getLogger(BandMember.class.getSimpleName());
    private final String name;
    private BandMember fellowBandMember;

    BandMember(String name) {
        this.name = name;
    }

    void addressFellowBandMember() {
        log.info("{} is addressing {}", name, fellowBandMember.getName());
        final String response = fellowBandMember.address("Hi from " + this.getName());
        log.info("{} recieved response '{}''", name, response);
    }

    /**
     * Invoked by other BandMembers
     */
    String address(String message) {
        log.info("{} received message '{}'", this.getName(), message);
        return "Yabadabadoo! (from " + this.getName() + ")";
    }

    String getName()  {
        return name;
    }

    void setFellowBandMember(BandMember fellowBandMember) {
        this.fellowBandMember = fellowBandMember;
    }

}
