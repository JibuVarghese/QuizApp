package com.jvm.quizapp.model;

public class Questions {

    public String questions[] = {
            "Which is a Programming Language?",
            "What year did Google purchased Android Inc.?",
            "What is the capital of India?"
    };

    public String[][] choices = {
            {"HTML", "CSS", "SUBLIME", "PHP"},
            {"2003", "2004", "2005", "2006"},
            {"NEW DELHI", "KARNATAKA", "UTTARPRADESH", "MAHARASTRA"}
    };

    public String[] correctAnswer = {
            "PHP",
            "2005",
            "NEW DELHI"
    };

    public String getQuestion(int a) {
        return questions[a];
    }

    public String getchoice1(int a) {
        return choices[a][0];
    }

    public String getchoice2(int a) {
        return choices[a][1];
    }

    public String getchoice3(int a) {
        return choices[a][2];
    }

    public String getchoice4(int a) {
        return choices[a][3];
    }

    public String getCorrectAnswer(int a) {
        return correctAnswer[a];
    }
}
