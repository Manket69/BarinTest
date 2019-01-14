package com.example.maren.braintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    public Button   but1 ;
    public TextView gameTextView;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        but1=findViewById(R.id.but1);
        gameTextView=findViewById(R.id.textView);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("gameScore", 0);

        gameTextView.setText("Completed : "+Integer.toString(intValue)+" BrainTest's");

    }
    public void onClick(View view) {
        Intent play = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(play);
    }
}
