package se.patrikbergman.java.concurrency.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BandMember extends Thread {
    final Logger log = LoggerFactory.getLogger(BandMember.class.getSimpleName());
    private final String name;
    private final long initialSleepTime;
    private BandMember fellowBandMember;

    BandMember(String name, long initialSleepTime) {
        this.name = name;
        this.initialSleepTime = initialSleepTime;
    }

    public void run() {
        doWait(initialSleepTime);
        addressFellowBandMember();
    }

    void addressFellowBandMember() {
        log.info("{} is addressing fellow band member {}",
                this.getFirstnameAndThreadName(),
                fellowBandMember.getFirstnameAndThreadName());
        String response = fellowBandMember.address("Hi from " + this.getFirstnameAndThreadName());
        log.info("{} recieved response '{}''", this.getFirstnameAndThreadName(), response);
    }

    /**
     * Invoked by other BandMembers
     */
    String address(String message) {
        if(this.getState().equals(State.TIMED_WAITING)) {
            log.info("{} received message while waiting...", this.getFirstnameAndThreadName());
        }
        log.info("{} received message '{}'", this.getFirstnameAndThreadName(), message);
        return "Yabadabadoo! (from " + this.getFirstnameAndThreadName() + ")";
    }

    String getFirstnameAndThreadName()  {
        return name + " [" + getName() + "]";
    }

    void setFellowBandMember(BandMember fellowBandMember) {
        this.fellowBandMember = fellowBandMember;
    }

    private void doWait(long waitTime) {
        try {
            sleep(waitTime);
        } catch (InterruptedException e) {
            log.error("{} was interrupted during wait", getFirstnameAndThreadName());
        }
    }
}
