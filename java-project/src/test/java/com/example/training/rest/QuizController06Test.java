package com.example.training.rest;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController06Test extends ControllerMockMvcTestBase {
    @Test
    public void restQuiz06() throws Exception {
        // get real file bytes from
        byte[] fileBytes = Files.readAllBytes(Paths.get("./", "files", "data", "hello.txt"));

        mockMvc.perform(get("/restQuiz06")
                .param("filePathName", "/data/hello.txt"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    byte[] content = mvcResult.getResponse().getContentAsByteArray();
                    Assert.assertArrayEquals(fileBytes, content);
                });

        // path traversal
        mockMvc.perform(get("/restQuiz06")
                .param("filePathName", "/../data/hello.txt"))
                .andExpect(status().is(403));

        // file not found
        mockMvc.perform(get("/restQuiz06")
                .param("filePathName", "/data/notfound.txt"))
                .andExpect(status().is(404));

        // is a folder
        mockMvc.perform(get("/restQuiz06")
                .param("filePathName", "/data"))
                .andExpect(status().is(404));
    }
}
