package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController03Test extends ControllerMockMvcTestBase {
    private MediaType contentType = MediaType.APPLICATION_JSON;

    @Test
    public void restQuiz03() throws Exception {
        mockMvc.perform(get("/restQuiz03")
                .param("name", "Optional GET")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("Hello, Optional GET!", greeting.getAnswer());
                });

        mockMvc.perform(get("/restQuiz03")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("Hello, World!", greeting.getAnswer());
                });

        mockMvc.perform(get("/restQuiz03")
                .param("name", "")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("Hello, !", greeting.getAnswer());
                });
    }
}