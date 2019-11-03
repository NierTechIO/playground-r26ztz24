package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController07 {
    @RequestMapping(
            value = "/example07/{name}/{text:[a-zA-Z0-9]+}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public RestQuiz01Response example07(@PathVariable String name, @PathVariable("text") String content) {
        RestQuiz01Response result = new RestQuiz01Response();
        result.setAnswer(name + ": " + content);
        return result;
    }
}
