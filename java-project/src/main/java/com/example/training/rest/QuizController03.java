// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController03 {
    @RequestMapping(value = "/restQuiz03", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public RestQuiz01Response restQuiz03(/* TODO: GET parameter */) {
        // TODO: Return Object
        return null;
    }
}
