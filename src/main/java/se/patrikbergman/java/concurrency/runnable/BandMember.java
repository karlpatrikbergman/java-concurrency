package se.patrikbergman.java.concurrency.runnable;

class BandMember extends Thread {
    private final String name;
    private final long initialSleepTime;
    private BandMember fellowBandMember;

    BandMember(String name, long initialSleepTime) {
        this.name = name;
        this.initialSleepTime = initialSleepTime;
    }

    public void run() {
//        doSleep(initialSleepTime);
        doWait(initialSleepTime);
        addressFellowBandMember();
    }

    void addressFellowBandMember() {
        System.out.printf("BandMember %s is addressing fellow band member %s%n", this.getFirstnameAndThreadName(),
                fellowBandMember.getFirstnameAndThreadName());
        fellowBandMember.address("Hi from " + this.getFirstnameAndThreadName());
    }

    /**
     * Invoked by other BandMembers
     */
    String address(String message) {
        if(this.getState().equals(State.TIMED_WAITING)) {
            System.out.printf("BandMember %s received message while waiting...%n", this.getFirstnameAndThreadName());
        }
        System.out.printf("BandMember %s received message '%s'%n", this.getFirstnameAndThreadName(), message);
        return "Response message from " + this.getFirstnameAndThreadName();
    }

    String getFirstnameAndThreadName()  {
        return name + " in " + getName();
    }

    void setFellowBandMember(BandMember fellowBandMember) {
        this.fellowBandMember = fellowBandMember;
    }

    private void doSleep(long sleepTime) {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.err.printf("%s was interrupted during sleep", getFirstnameAndThreadName());
        }
    }

    private void doWait(long waitTime) {
        try {
            sleep(waitTime);
        } catch (InterruptedException e) {
            System.err.printf("%s was interrupted during wait", getFirstnameAndThreadName());
        }
    }

//    private void randomSleep() {
//        doSleep(RandomUtils.nextLong(1000, 5000));
//    }

}
