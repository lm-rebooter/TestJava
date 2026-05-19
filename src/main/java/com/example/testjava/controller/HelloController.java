package com.example.testjava.controller;

import com.example.testjava.dto.HelloResponse;
import com.example.testjava.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/api/hello")
    public HelloResponse hello(@RequestParam(value = "name", required = false) String name) {
        return helloService.sayHello(name);
    }


    @GetMapping("/api/hello1")
    public String hello1() {
        return "Hello111, TestJava!";
    }
}
