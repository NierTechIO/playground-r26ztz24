// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController01 {
    @RequestMapping(value = "/restQuiz01", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public RestQuiz01Response restQuiz01() {
        // TODO: Please return JSON with {"answer": "Hello, REST!"}
        RestQuiz01Response result = new RestQuiz01Response();
        result.setAnswer("5566 Never Dies.");
        return result;
    }
}
