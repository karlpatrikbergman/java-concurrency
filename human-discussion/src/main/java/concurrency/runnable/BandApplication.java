package concurrency.runnable;

class BandApplication {

    public static void main(String args[]) {
        BandMember john = new BandMember("John");
        BandMember paul = new BandMember("Paul");

        john.setFellowBandMember(paul);
        paul.setFellowBandMember(john);

        new Thread(john::addressFellowBandMember).start();

        new Thread(paul::addressFellowBandMember).start();
    }
}
