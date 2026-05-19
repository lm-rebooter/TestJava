package com.example.testjava.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsDefaultMessage() throws Exception {
        mockMvc.perform(get("/test/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, TestJava!"))
                .andExpect(jsonPath("$.source").value("in-memory"));
    }

    @Test
    void helloReturnsMessageForRequestName() throws Exception {
        mockMvc.perform(get("/test/api/hello").param("name", "Codex"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Codex!"))
                .andExpect(jsonPath("$.source").value("in-memory"));
    }
}
