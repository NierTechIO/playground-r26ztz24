package com.example.training.rest;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController08Test extends ControllerMockMvcTestBase {
    @Test
    public void restQuiz08() throws Exception {
        mockMvc.perform(get("/restQuiz08?error=201"))
                .andExpect(status().is(201));

        mockMvc.perform(get("/restQuiz08?error=403"))
                .andExpect(status().is(403));

        mockMvc.perform(get("/restQuiz08?error=5566"))
                .andExpect(status().is(400));
    }
}
