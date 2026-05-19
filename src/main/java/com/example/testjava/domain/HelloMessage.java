package com.example.testjava.domain;

public class HelloMessage {

    private final String recipient;
    private final String content;
    private final String source;

    public HelloMessage(String recipient, String content, String source) {
        this.recipient = recipient;
        this.content = content;
        this.source = source;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }
}
