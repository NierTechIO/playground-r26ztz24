package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController07 {
    /* TODO: update value to accept "/restQuiz07/{name}:{text}" format.
        name format is a-z only, without upper cases and numbers.
        text can contains any characters
        return RestQuiz01Response with answer "{name} says {text}"
     */
    @RequestMapping(value = "/answerQuiz07/{name:[a-z]+}:{text}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public RestQuiz01Response restQuiz07(@PathVariable String name, @PathVariable String text) {
        RestQuiz01Response result = new RestQuiz01Response();
        result.setAnswer(name + " says " + text);
        return result;
    }
}
