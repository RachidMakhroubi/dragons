package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class TheEnd extends Activity {

    TextView score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);
        int finalscore = getIntent().getExtras().getInt("highest score");

        score = (TextView) findViewById(R.id.DisplayScore);

        score.setText(""+finalscore);

    }
    public void startOver(View view){
       Intent intent = new Intent(TheEnd.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void quit(View view){
        finish();
    }
}

