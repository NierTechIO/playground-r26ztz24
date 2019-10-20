package com.example.training;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by charlotte on 06/06/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    private MediaType contentType = MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingsWithName() throws Exception {
        mockMvc.perform(post("/greeting").param("name", "World")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    Greeting greeting = new ObjectMapper().readValue(responseJson, Greeting.class);
                    //assertEquals("{\"id\":1,\"content\":\"Hello, World!\"}",responseJson );
                    assertEquals(1, greeting.getId());
                    assertEquals("Hello, World!", greeting.getContent());
                });
    }
}
