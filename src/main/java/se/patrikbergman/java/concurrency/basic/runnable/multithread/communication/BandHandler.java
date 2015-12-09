package se.patrikbergman.java.concurrency.basic.runnable.multithread.communication;

class BandHandler implements Runnable {

    private final Band band;

    BandHandler(final Band band) {
        this.band = band;
    }

    @Override
    public void run() {
        band.setName("Judas Priest");
        System.out.printf("%s is running in %s", BandHandler.class.getSimpleName(), Thread.currentThread().getId());
    }

    Band getBand() {
        return band;
    }
}
