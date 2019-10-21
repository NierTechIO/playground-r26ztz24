// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import com.example.training.to.RestQuiz04Request;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController04 {
    @RequestMapping(value = "/restQuiz04", produces = MediaType.APPLICATION_JSON_VALUE /* TODO: set method */)
    public RestQuiz01Response restQuiz04(/* TODO: POST Parameter RestQuiz04Request */) {
        // TODO: Read "name" from POST parameter, response with "Hello, %name%!"
        return null;
    }
}
