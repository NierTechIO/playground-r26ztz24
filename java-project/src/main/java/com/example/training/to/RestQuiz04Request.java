package com.example.training.to;

import java.io.Serializable;

public class RestQuiz04Request implements Serializable {
    private static final long serialVersionUID = -5732115588561149893L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
