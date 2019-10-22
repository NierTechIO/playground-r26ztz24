package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController05Test extends ControllerMockMvcTestBase {
    private MediaType contentType = MediaType.APPLICATION_JSON;

    @Test
    public void restQuiz05() throws Exception {
        String fileContent = "Hello, Upload File with POST!";
        MockMultipartFile file = new MockMultipartFile("file", "helloFile.txt", "text/plain", fileContent.getBytes());

        mockMvc.perform(multipart("/restQuiz05")
                .file(file)
                .param("name", "Nier"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("success", greeting.getAnswer());
                });

        // check file
        Path filePath = Paths.get("./Nier/helloFile.txt");
        Assert.assertTrue(Files.exists(filePath));
        byte[] fileBytes = Files.readAllBytes(filePath);
        Assert.assertArrayEquals(fileContent.getBytes(), fileBytes);

        // remove file
        Files.delete(filePath);
        Files.delete(filePath.getParent());

        // file traversal
        MockMultipartFile pathTraversalFile = new MockMultipartFile("file", "helloFile.txt", "text/plain", fileContent.getBytes());

        mockMvc.perform(multipart("/restQuiz05")
                .file(pathTraversalFile)
                .param("name", "../Nier"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("path traversal", greeting.getAnswer());
                });

        // file too large
        String largeFileContent = "123456789012345678901234567890123456789012345678901234567890";
        MockMultipartFile largeFile = new MockMultipartFile("file", "helloFile.txt", "text/plain", largeFileContent.getBytes());

        mockMvc.perform(multipart("/restQuiz05")
                .file(largeFile)
                .param("name", "Nier"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String responseJson = mvcResult.getResponse().getContentAsString();
                    RestQuiz01Response greeting = new ObjectMapper().readValue(responseJson, RestQuiz01Response.class);
                    assertEquals("large file", greeting.getAnswer());
                });
    }
}
