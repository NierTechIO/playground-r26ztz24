package com.example.training.rest;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizController06Test extends ControllerMockMvcTestBase {
    @Test
    public void restQuiz06() throws Exception {
        // write bytes to file
        Path filePath = Paths.get("./", "files", "data", "hello.txt");
        filePath.toFile().getParentFile().mkdirs();
        byte[] fileBytes = "Hello, download file!!".getBytes();
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(fileBytes);
        }

        mockMvc.perform(get("/restQuiz06")
                .param("filePathName", "/data/hello.txt"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    byte[] content = mvcResult.getResponse().getContentAsByteArray();
                    Assert.assertArrayEquals(fileBytes, content);
                });

        // remove test file
        FileUtils.deleteDirectory(new File("./files"));

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
