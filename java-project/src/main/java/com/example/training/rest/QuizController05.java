// { autofold
package com.example.training.rest;

import com.example.training.to.RestQuiz01Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//}

@RestController
public class QuizController05 {
    @RequestMapping(value = "/restQuiz05", method = RequestMethod.POST)
    public RestQuiz01Response restQuiz05(/* TODO: Multipart File with name "file", and a param "name" */) {
        RestQuiz01Response result = new RestQuiz01Response();

        // TODO: Check path traversal is under current foler "./", if failed, return answer "path traversal"

        // TODO: check file size must <= 50 bytes, if failed, return answer "large file"

        // TODO: create folders for file "./%name%/%fileName%"

        // TODO: write file "./%name%/%fileName%" to disk, if success return answer "success", otherwise, return "failed"

        return result;
    }
}
