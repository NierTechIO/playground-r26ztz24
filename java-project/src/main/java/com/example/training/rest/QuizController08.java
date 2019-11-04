package com.example.training.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class QuizController08 {
    @RequestMapping(value = "/restQuiz08", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public void restQuiz08(@RequestParam("error") String error) throws Exception {
        if ("403".equals(error)) {
            throw new AccessDeniedException("Access denied.");
        } else if ("201".equals(error)) {
            throw new NullPointerException("Null pointer.");
        } else{
            throw new Exception("Else");
        }
    }
}
