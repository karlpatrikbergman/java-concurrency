package concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class BandMember {
    private final String name;
    private BandMember fellowBandMember;

    BandMember(String name) {
        this.name = name;
    }

    void addressFellowBandMember() {
        log.info("{} ({}) is addressing {}", name, this, fellowBandMember.getName());
        final String response = fellowBandMember.address("Hi from " + this.getName());
        log.info("{} ({}) recieved response '{}''", name, this, response);
    }

    /**
     * Invoked by other BandMembers
     */
    String address(String message) {
        log.info("{} ({}) received message '{}'", name, this, message);
        return "Yabadabadoo! (from " + this.getName() + ")";
    }

    String getName()  {
        return name;
    }

    void setFellowBandMember(BandMember fellowBandMember) {
        this.fellowBandMember = fellowBandMember;
    }

}
