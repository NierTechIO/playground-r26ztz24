package com.example.training.to;

import java.io.Serializable;

public class RestQuiz01Response implements Serializable {
    private static final long serialVersionUID = 2806107932088184191L;

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
