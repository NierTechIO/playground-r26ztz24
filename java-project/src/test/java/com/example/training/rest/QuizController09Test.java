package com.example.training.rest;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController09Test extends ControllerMockMvcTestBase {
    @Test
    public void restQuiz09() throws Exception {
        mockMvc.perform(options("/restQuiz09")
                .header("Access-Control-Request-Method", "GET")
                .header("Origin", "https://example.com"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "https://example.com"))
                .andExpect(header().string("Access-Control-Max-Age", "6000"));

        mockMvc.perform(get("/restQuiz09")
                .header("Origin", "https://example.com"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/restQuiz09")
                .header("Origin", "https://example2.com"))
                .andExpect(status().is(403));
    }
}
