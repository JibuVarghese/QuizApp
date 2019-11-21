
package com.jvm.quizapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizQuestions {

    @SerializedName("Questions")
    @Expose
    private List<Questions> questions = null;

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

}
