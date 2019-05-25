package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Play(View view){
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.gs);
        mPlayer.start();
       Intent intent = new Intent(this, Play.class);

       startActivity(intent);

       finish();

    }


}
