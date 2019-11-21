package com.jvm.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.jvm.quizapp.adapter.ResultAdapter;
import com.jvm.quizapp.model.QuizResults;
import com.jvm.quizapp.model.Result;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {
    LinearLayoutManager mLayoutManager;
    RecyclerView resultRecyclerView;
    ResultAdapter resultAdapter;
    ArrayList<Result> results = new ArrayList<>();
    String resultString;
    QuizResults quizResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (getIntent().getExtras() != null) {
            resultString = getIntent().getExtras().getString("Result");
            Gson gson = new Gson();
            quizResults = gson.fromJson(resultString,QuizResults.class);
        }

        resultRecyclerView = findViewById(R.id.resultRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        resultRecyclerView.setLayoutManager(mLayoutManager);
        resultAdapter = new ResultAdapter(results);
        resultRecyclerView.setAdapter(resultAdapter);

        if (quizResults != null){
            results.addAll(quizResults.getResults());
            resultAdapter.notifyDataSetChanged();
        }
    }
}
