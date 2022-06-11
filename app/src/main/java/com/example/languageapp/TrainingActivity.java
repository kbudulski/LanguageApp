package com.example.languageapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class TrainingActivity extends AppCompatActivity {

    private TextView textViewTop;
    private TextView textViewBottom;
    private HashMap<String, String> wordsMap;
    private List<String> keyList;
    private Random generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_training);

        Bundle extras = getIntent().getExtras();
        String language = extras.getString("lang");

        textViewTop = findViewById(R.id.textViewTop);
        textViewBottom = findViewById(R.id.textViewBottom);
        wordsMap = Words.createMockedWordsMap(language);
        generator = new Random();
        Set<String> keySet = wordsMap.keySet();
        keyList = new ArrayList<>(keySet);
        getNextRandomWordsPair();

        Button buttonNextWord = findViewById(R.id.buttonNextWord);

        buttonNextWord.setOnClickListener(v -> getNextRandomWordsPair());
    }

    private void getNextRandomWordsPair() {
        String randomKey;
        String randomValue;
        do {
            int randomIndex = generator.nextInt(keyList.size());
            randomKey = keyList.get(randomIndex);
            randomValue = wordsMap.get(randomKey);
        } while (randomKey == textViewTop.getText());

        textViewTop.setText(randomKey);
        textViewBottom.setText(randomValue);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Czy chcesz zakończyć?");
        builder.setPositiveButton("Tak", (dialog, which) -> finish());
        builder.setNegativeButton("Nie", (dialog, which) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}

class Words {
    public static HashMap<String, String> createMockedWordsMap(String lang) {
        HashMap<String, String> mockedWordsMap = new HashMap<>();
        mockedWordsMap.put("Zielony", "Green");
        mockedWordsMap.put("Drewno", "Wood");
        mockedWordsMap.put("Klawiatura", "Keyboard");
        mockedWordsMap.put("Szklanka", "Glass");
        mockedWordsMap.put("Pies", "Dog");
        mockedWordsMap.put("Jabłko", "Apple");
        if (lang.equals("angielski")) {
            return mockedWordsMap;
        } else {
            HashMap<String, String> reversedMockedWordsMap = new HashMap<>();
            for (Map.Entry<String, String> entry : mockedWordsMap.entrySet())
                reversedMockedWordsMap.put(entry.getValue(), entry.getKey());
            return reversedMockedWordsMap;
        }
    }

}