package com.example.testjava.repository.impl;

import com.example.testjava.domain.HelloMessage;
import com.example.testjava.repository.HelloRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryHelloRepository implements HelloRepository {

    @Override
    public HelloMessage findHelloMessage(String recipient) {
        return new HelloMessage(recipient, "Hello, " + recipient + "!", "in-memory");
    }
}
