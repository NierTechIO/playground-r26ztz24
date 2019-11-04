package com.example.training.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController09 {
    /* TODO: Add CORS, accept origins are "https://example.com" and max-age is 10 minutes. */
    @RequestMapping(value = "/restQuiz09", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String restQuiz09() {
        return "Hello, CrossOrigin";
    }
}
