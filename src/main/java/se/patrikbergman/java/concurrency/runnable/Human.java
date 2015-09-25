package se.patrikbergman.java.concurrency.runnable;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

class Human implements Runnable {
    private final String name;
    private boolean havingConversation = false;
    List<Human> fellowHumans = new ArrayList<>();
    List<Message> messageHistory = new ArrayList<>();

    Human(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        live();
    }

    private void live() {
        while(true) {
            randomSleep();
            addressFellowHuman();
        }
    }

    void addFellowHuman(Human human) {
        fellowHumans.add(human);
    }

    void address(Message message, Callable<Message> messageResponder) {
        try {
            havingConversation = true;
            messageHistory.add(message);
            randomSleep();
            messageResponder.call();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            havingConversation = false;
        }
    }

    private void addressFellowHuman() {
        findFellowHumanAvailableForConversation()
                .ifPresent(this::addressFellowHuman);
    }

    private void addressFellowHuman(final Human fellowHuman) {
        final int conversationId = RandomUtils.nextInt(0, 500);
        final String initialMessage = RandomStringUtils.randomAlphabetic(5);
        fellowHuman.address(new Message(conversationId, initialMessage),
                () -> new Message(conversationId, RandomStringUtils.randomAlphabetic(5)));
        waitForResponse();
    }

    private Optional<Human> findFellowHumanAvailableForConversation() {
        return fellowHumans.stream()
                .filter(human -> !human.isHavingConversation())
                .findFirst();
    }

    boolean isHavingConversation() {
        return havingConversation;
    }

    private void waitForResponse() {
        try {
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void randomSleep() {
        try {
            Thread.sleep(RandomUtils.nextLong(1000, 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
