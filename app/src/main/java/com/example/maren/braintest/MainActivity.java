package com.example.maren.braintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView timerTextView = findViewById(R.id.timerTextView);  // Odliczanie Czasu
        TextView scoreTextView = findViewById(R.id.scoreTextView);  // Wynik Odpowiedzi poprawne / Zadane pytania
        TextView taskTextView = findViewById(R.id.taskTextView);    // Pytanie
        TextView answerTextView = findViewById(R.id.answerTextView);// Odpowiedz Poprawana / Nie poprawna

        // Pola odpowiedzi
        // 1  2
        // 3  4

        TextView caseTextView1 = findViewById(R.id.caseTextView1);
        TextView caseTextView2 = findViewById(R.id.caseTextView2);
        TextView caseTextView3 = findViewById(R.id.caseTextView3);
        TextView caseTextView4 = findViewById(R.id.caseTextView4);







    }



    public void ChooseCase(View view) {


       TextView answerText = findViewById(view.getId());
       String textPressed = answerText.getText().toString();



        Log.d("Wybor ", "To "+textPressed);

    }
}
