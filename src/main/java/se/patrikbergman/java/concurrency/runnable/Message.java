package se.patrikbergman.java.concurrency.runnable;

class Message {
    final int conversationId;
    final String message;

    Message(int conversationId, String message) {
        this.conversationId = conversationId;
        this.message = message;
    }
}
