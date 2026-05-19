package com.example.testjava.service.impl;

import com.example.testjava.domain.HelloMessage;
import com.example.testjava.dto.HelloResponse;
import com.example.testjava.repository.HelloRepository;
import com.example.testjava.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    private static final String DEFAULT_NAME = "TestJava";

    private final HelloRepository helloRepository;

    public HelloServiceImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public HelloResponse sayHello(String name) {
        String recipient = normalizeName(name);
        HelloMessage helloMessage = helloRepository.findHelloMessage(recipient);
        return new HelloResponse(helloMessage.getContent(), helloMessage.getSource());
    }

    private String normalizeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return DEFAULT_NAME;
        }
        return name.trim();
    }
}

