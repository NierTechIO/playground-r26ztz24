package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController01Test extends ControllerMockMvcTestBase {
    private MediaType contentType = MediaType.APPLICATION_JSON;

    @Test
    public void restQuiz01() throws Exception {
        mockMvc.perform(get("/restQuiz01")
                .param("name", "World")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("Hello, World!", greeting.getAnswer());
                });
    }
}