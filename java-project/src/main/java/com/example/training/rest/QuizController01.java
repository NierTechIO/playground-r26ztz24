// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController01 {
    @RequestMapping(value = "/restQuiz01", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public RestQuiz01Response restQuiz01(@RequestParam("name") String name) {
        // TODO: Please return JSON with {"answer": "Hello, $name!"}
        return new RestQuiz01Response();
    }
}
