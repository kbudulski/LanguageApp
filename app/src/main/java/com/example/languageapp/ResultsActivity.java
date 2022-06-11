package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ResultsActivity extends AppCompatActivity {

    private String scorePercentage = "0%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_results);

        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");
        int totalNumberOfGuesses = extras.getInt("totalNumberOfGuesses");
        setScoresToDisplay(score, totalNumberOfGuesses);
    }

    @SuppressLint("DefaultLocale")
    private void setScoresToDisplay(int score, int totalNumberOfGuesses) {
        try {
            scorePercentage = Integer.toString(score * 100 / totalNumberOfGuesses) + '%';
        } catch (Exception ignored) {
        }
        TextView textViewScore = findViewById(R.id.textViewScore);
        TextView textViewRatio = findViewById(R.id.textViewRatio);
        textViewScore.setText(scorePercentage);
        textViewRatio.setText(String.format("%s/%d", score, totalNumberOfGuesses));
    }
}