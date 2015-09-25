package se.patrikbergman.java.concurrency.runnable;

import org.apache.commons.lang3.RandomUtils;

class BandMember extends Thread {
    private final String name;
    private final long initialSleepTime;
    private BandMember fellowBandMember;

    BandMember(String name, long initialSleepTime) {
        this.name = name;
        this.initialSleepTime = initialSleepTime;
    }

    public void run() {
        doSleep(initialSleepTime);
        addressFellowBandMember();
    }

    void addressFellowBandMember() {
        System.out.printf("BandMember %s is addressing fellow band member %s%n", name, fellowBandMember.getFirstname());
        fellowBandMember.address("Hi from " + name);
    }

    /**
     * Invoked by other BandMembers
     */
    String address(String message) {
        System.out.printf("BandMember %s received message '%s'%n", name, message);
        return "Response message from " + name;
    }

    public String getFirstname()  {
        return name;
    }

    void setFellowBandMember(BandMember fellowBandMember) {
        this.fellowBandMember = fellowBandMember;
    }

    private void randomSleep() {
        doSleep(RandomUtils.nextLong(1000, 5000));
    }

    private void doSleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
