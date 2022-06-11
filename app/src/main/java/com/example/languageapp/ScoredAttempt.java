package com.example.languageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;


public class ScoredAttempt extends AppCompatActivity {

    private TextView textViewTop;
    private EditText editTextBottom;
    private HashMap<String, String> wordsMap;
    private List<String> keyList;
    private Random generator;
    private int correctGuesses;
    private int totalNumberOfGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_scored_attempt);

        Bundle extras = getIntent().getExtras();
        String language = extras.getString("lang");

        textViewTop = findViewById(R.id.textViewTop);
        editTextBottom = (EditText) findViewById(R.id.editTextWordInput);
        wordsMap = Words.createMockedWordsMap(language);
        generator = new Random();
        Set<String> keySet = wordsMap.keySet();
        keyList = new ArrayList<>(keySet);
        correctGuesses = 0;
        totalNumberOfGuesses = 0;
        getNextRandomWord();

        Button buttonNextWord = findViewById(R.id.buttonNextWord);
        Button buttonSaveAttempt = findViewById(R.id.buttonSaveAttempt);

        buttonNextWord.setOnClickListener(v -> onNextWord());
        buttonSaveAttempt.setOnClickListener(v -> navigateForward());
    }

    private void onNextWord() {
        updateScore();
        getNextRandomWord();
    }

    private void updateScore() {
        totalNumberOfGuesses++;
        final String correctAnswer = Objects.requireNonNull(wordsMap.get(textViewTop.getText().toString())).toUpperCase();
        final String givenAnswer = editTextBottom.getText().toString().toUpperCase();
        if (givenAnswer.equals(correctAnswer)) {
            correctGuesses++;
        }
    }

    private void getNextRandomWord() {
        String randomKey;
        do {
            int randomIndex = generator.nextInt(keyList.size());
            randomKey = keyList.get(randomIndex);
        } while (randomKey == textViewTop.getText());

        textViewTop.setText(randomKey);
        editTextBottom.setText("");
    }


    private void navigateForward() {
        Intent i = new Intent(getApplicationContext(), ResultsActivity.class);
        i.putExtra("score", correctGuesses);
        i.putExtra("totalNumberOfGuesses", totalNumberOfGuesses);
        startActivity(i);
    }

}