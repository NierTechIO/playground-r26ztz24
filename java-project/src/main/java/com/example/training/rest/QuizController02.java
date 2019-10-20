// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController02 {
    @RequestMapping(value = "/restQuiz02", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public RestQuiz01Response restQuiz02(/* TODO: add GET parameter "name" here */) {
        // TODO: Please return JSON with {"answer": "Hello, %name%!"}
        RestQuiz01Response result = new RestQuiz01Response();
        result.setAnswer("5566 Never Dies.");
        return result;
    }
}
