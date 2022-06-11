package com.example.languageapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup languageGroup;
    private RadioGroup modeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Prawie Duolingo");
        setContentView(R.layout.activity_main);
        languageGroup = (RadioGroup) findViewById(R.id.radioGroupLanguage);
        modeGroup = (RadioGroup) findViewById(R.id.radioGroupMode);
        languageGroup.check(R.id.radioButtonEnglish);
        modeGroup.check(R.id.radioButtonTraining);

        Button btnNext = (Button) findViewById(R.id.button);
        Button btnAbout = (Button) findViewById(R.id.buttonAbout);

        btnAbout.setOnClickListener(v -> showAboutDialog());
        btnNext.setOnClickListener(v -> navigateForward());
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("O programie");
        builder.setMessage("Prawie Duolingo\nWersja 1.0.0\nWykonał Krystian Budulski");
        builder.setNegativeButton("Zamknij", (dialog, which) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void navigateForward() {
        int selectedLanguage = languageGroup.getCheckedRadioButtonId();
        int selectedMode = modeGroup.getCheckedRadioButtonId();
        RadioButton languageRadioButton = (RadioButton) findViewById(selectedLanguage);
        RadioButton modeRadioButton = (RadioButton) findViewById(selectedMode);
        if (modeRadioButton.getText().equals("nauka")) {
            Intent i = new Intent(getApplicationContext(), TrainingActivity.class);
            i.putExtra("lang", languageRadioButton.getText());
            startActivity(i);
        } else {
            Intent i = new Intent(getApplicationContext(), ScoredAttempt.class);
            i.putExtra("lang", languageRadioButton.getText());
            startActivity(i);
        }
    }
}