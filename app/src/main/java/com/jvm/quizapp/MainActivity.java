package com.jvm.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jvm.quizapp.model.Questions;
import com.jvm.quizapp.model.QuizResults;
import com.jvm.quizapp.model.Result;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_one, btn_two, btn_three, btn_four;
    TextView tv_question;

    private Questions questions = new Questions();

    private String answer;
    private int questionLength = questions.questions.length;
    int currentQuestion = 0;

    QuizResults quizResults = new QuizResults();
    ArrayList<Result> results = new ArrayList<>();

    //Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //random = new Random();
        btn_one = findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_four = findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);

        tv_question = findViewById(R.id.tv_question);

        NextQuestion(currentQuestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                showResponse(btn_one.getText());
                break;

            case R.id.btn_two:
                showResponse(btn_two.getText());
                break;

            case R.id.btn_three:
                showResponse(btn_three.getText());
                break;

            case R.id.btn_four:
                showResponse(btn_four.getText());
                break;
        }
    }

    private void showResponse(CharSequence text){
        Result result = new Result();
        result.setQuestion(tv_question.getText().toString());
        result.setUserChoice(text.toString());
        result.setAnswer(answer);

        results.add(result);
        if (text == answer) {
            Toast.makeText(MainActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
            NextQuestion(currentQuestion++);
        } else {
            Toast.makeText(MainActivity.this, "You Are Wrong", Toast.LENGTH_SHORT).show();
            GameOver();
        }
    }

    private void GameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Quiz Over")
                .setCancelable(false)
                .setNeutralButton("See Result", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        quizResults.setResults(results);
                        Intent intent = new Intent(MainActivity.this, Results.class);
                        intent.putExtra("Result", new Gson().toJson(quizResults));
                        startActivity(intent);
                    }
                })
                .setPositiveButton("Start New Quiz Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialogBuilder.show();

    }

    private void NextQuestion(int num) {
        if (num < questionLength) {
            currentQuestion++;
            tv_question.setText(questions.getQuestion(num));
            btn_one.setText(questions.getchoice1(num));
            btn_two.setText(questions.getchoice2(num));
            btn_three.setText(questions.getchoice3(num));
            btn_four.setText(questions.getchoice4(num));

            answer = questions.getCorrectAnswer(num);
        } else if (num == questionLength) {
            int num1 = num - 1;
            tv_question.setText(questions.getQuestion(num1));
            btn_one.setText(questions.getchoice1(num1));
            btn_two.setText(questions.getchoice2(num1));
            btn_three.setText(questions.getchoice3(num1));
            btn_four.setText(questions.getchoice4(num1));

            answer = questions.getCorrectAnswer(num1);
        } else {
            //Toast.makeText(MainActivity.this, "You Are Wrong", Toast.LENGTH_SHORT).show();
            GameOver();
        }
    }

    public void getQuestionList() {
        Gson gson = new Gson();
        Questions questions = gson.fromJson(loadJSONFromAsset(this), Questions.class);
    }

    public static String loadJSONFromAsset(Context mContext) {
        String json;
        try {
            InputStream is = mContext.getAssets().open("quiz.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
