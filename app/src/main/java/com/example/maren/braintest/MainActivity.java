package com.example.maren.braintest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int rightAnswerCount = 0;
    int quizCount=1;
    String rightAnswer;
    int QUIZ_COUNT = 5 ;
    String textPressed ;
    int tickCount = 1 ;
    int stopCount = 1;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"Działanie" , " Poprwana Odpowiedz", "Odpowiedz 1 " , Odpowiedz2" , "Odpowiedz 3 " }
            {"2 + 5","7","22","28","2"},
            {"11 + 13","24","33","19","39"},
            {"16 + 19","35","24","37","4"},
            {"2 - 4","-2","2","0","8"},
            {"32 - 15","17","18","16","0"},
            {"2 + 2 x 2","6","8","4","16"},
            {"3 + 3 - 3","3","0","-3","6"}
    };
    TextView timerTextView;
    TextView scoreTextView;
    TextView taskTextView;
    TextView answerTextView;
    TextView caseTextView1;
    TextView caseTextView2;
    TextView caseTextView3;
    TextView caseTextView4;
    Button start;
    Button button ;
    //TextView gameTextView;
    ImageView winnerImageView;
    boolean isRunning = false ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        timerTextView = findViewById(R.id.timerTextView);  // Odliczanie Czasu
         scoreTextView = findViewById(R.id.scoreTextView);  // Wynik Odpowiedzi poprawne / Zadane pytania
        taskTextView = findViewById(R.id.taskTextView);    // Pytanie
        answerTextView = findViewById(R.id.answerTextView);// Odpowiedz Poprawana / Nie poprawna
        button = findViewById(R.id.button);
        // Pola odpowiedzi
        // 1  2
        // 3  4

        caseTextView1 = findViewById(R.id.caseTextView1);
        caseTextView2 = findViewById(R.id.caseTextView2);
        caseTextView3 = findViewById(R.id.caseTextView3);
        caseTextView4 = findViewById(R.id.caseTextView4);
       // gameTextView = findViewById(R.id.gameTextView);
        answerTextView.setText("Choose the right one !");
        start = findViewById(R.id.button2);
        winnerImageView = findViewById(R.id.winnerimageView);
    // Tworzenie quizArray z quizData
        for(int i = 0; i < quizData.length; i++){
            //Przygotowanie
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // Działanie
            tmpArray.add(quizData[i][1]); // Poprawna Odpowiedz
            tmpArray.add(quizData[i][2]); // Odp1
            tmpArray.add(quizData[i][3]); // Odp2
            tmpArray.add(quizData[i][4]); //Odp3

            //dodanie tmpArray do quizArray
            quizArray.add(tmpArray);

        }


    }



    public void showNextQuiz(){

        stopCount++;
        tickCount++;
        if(stopCount < 7 ) {
            scoreTextView.setText(rightAnswerCount+"/"+Integer.toString(quizCount));
        }


        new CountDownTimer(6000, 100) {


            public void onTick(long millisUntilFinished) {
                if(isRunning){
                    cancel();

                    isRunning= false ;

                }else{

                    timerTextView.setText("0:" + millisUntilFinished / 100);
                }

            if(stopCount == 7){
                cancel();
            } }

            public void onFinish() {
                tickCount++;
                quizCount++;

                if(tickCount == 7 ){


                    cancel();

                    MediaPlayer lose = MediaPlayer.create(getApplicationContext(),R.raw.lose);
                    button.setText("Reset Game!");
                    button.setVisibility(View.VISIBLE);
                    caseTextView1.setVisibility(View.INVISIBLE);
                    caseTextView2.setVisibility(View.INVISIBLE);
                    caseTextView3.setVisibility(View.INVISIBLE);
                    caseTextView4.setVisibility(View.INVISIBLE);
                    //winnerImageView.setVisibility(View.VISIBLE);
                    taskTextView.setVisibility(View.INVISIBLE);
                    scoreTextView.setText(rightAnswerCount+"/"+QUIZ_COUNT);
                    answerTextView.setText("Time Up. Test Finished");
                    lose.start();
                }
                else{
                    if(tickCount <= 2) {
                        answerTextView.setText("Hurry Up!!");
                    }else if (tickCount <= 3 ) {
                        answerTextView.setText("WAKE UP!!");
                    }else if (tickCount <=4){
                        answerTextView.setText("Hello ????");
                    }else if (tickCount == 5 ){
                        answerTextView.setText("Press the Button ??");
                    }

                    tickCount--;
                   showNextQuiz();
              }
            }

        }.start();




        //Update taskTextView

        //Randomowa liczba 0 do 6
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Wybierz zestaw
        ArrayList<String> quiz = quizArray.get(randomNum);


        //Wybierz pytanie i wlasciwa odpowiedz
        if(stopCount < 7) {
            taskTextView.setText(quiz.get(0));
        }
        rightAnswer = quiz.get(1);

        //asdasd
        quiz.remove(0);
        Collections.shuffle(quiz);

        caseTextView1.setText(quiz.get(0));
        caseTextView2.setText(quiz.get(1));
        caseTextView3.setText(quiz.get(2));
        caseTextView4.setText(quiz.get(3));

        //Usun Powtorke
        quizArray.remove(randomNum);



    }


    public void ChooseCase(View view) {


       TextView answerText = findViewById(view.getId());
       textPressed = answerText.getText().toString();







        if(textPressed.equals(rightAnswer)){
        //Prawidlowe

           // timerTextView.setText("0:00");
            answerTextView.setText("Correct!");
            rightAnswerCount++;
        }else{
            // NiePrawidlowe
          //  timerTextView.setText("0:00");
        answerTextView.setText("Wrong...");

        }

        if(quizCount == QUIZ_COUNT){
            //Pokaz wynik
            MediaPlayer lose = MediaPlayer.create(getApplicationContext(),R.raw.lose);
            MediaPlayer win = MediaPlayer.create(getApplicationContext(),R.raw.win);
            button.setText("Reset Game!");
            button.setVisibility(View.VISIBLE);
            caseTextView1.setVisibility(View.INVISIBLE);
            caseTextView2.setVisibility(View.INVISIBLE);
            caseTextView3.setVisibility(View.INVISIBLE);
            caseTextView4.setVisibility(View.INVISIBLE);
            taskTextView.setVisibility(View.INVISIBLE);

            scoreTextView.setText(rightAnswerCount+"/"+quizCount);

            if(rightAnswerCount <= 2) {
                answerTextView.setText("Keep Trying!!");
                lose.start();
            }else if (rightAnswerCount <= 3 ) {
                answerTextView.setText("Work Harder !!");
                lose.start();
            }else if (rightAnswerCount <=4){
                answerTextView.setText("You Are Close");
                lose.start();
            }else if (rightAnswerCount == 5 ){
                answerTextView.setText("You Finished The Test !");
                winnerImageView.setVisibility(View.VISIBLE);
                win.start();
            }


        }else{
            quizCount++;
            isRunning=true;

            showNextQuiz();
        }




        Log.d("Wybor ", "To "+textPressed);

    }



    public void Reset(View view) {
      //  int gameCount = 1;


    button.setVisibility(View.INVISIBLE);

    Intent intent = new Intent(this,MainActivity.class);
    //intent.putExtra("gameScore",gameCount);
        // gameCount++;
       // gameTextView.setText("Game Number : "+Integer.toString(gameCount));
    startActivity(intent);

    }

    public void Start(View view) {
        caseTextView1.setVisibility(View.VISIBLE);
        caseTextView2.setVisibility(View.VISIBLE);
        caseTextView3.setVisibility(View.VISIBLE);
        caseTextView4.setVisibility(View.VISIBLE);

        scoreTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        taskTextView.setVisibility(View.VISIBLE);
        answerTextView.setVisibility(View.VISIBLE);

        start.setVisibility(View.INVISIBLE);

        showNextQuiz();

    }


}
