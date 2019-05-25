package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
import java.util.Random;
import android.os.Handler;

public class ViewClass extends View {
    Bitmap spear;
    Bitmap backView;
    Random random;
    Rect screen; // used for the coordinate of the screen
    static int width, heigh;
    Context context;
    int score = 0, highestScore=0; // to keep track of the score
    SoundPool sound;// for the song
    int spearSong = 0, deadSong = 0, gameSong = 0;
    ArrayList<Dragon> dragons;// create array of dragons
    ArrayList<Dragon> bats; // create array of green dragons
    ArrayList<Spear> spears; // create array of spears
    Paint result;

     // int dragonWidth; // to get the width of the dragon

    Handler handler; // to allow runnable to be executed in the future
    Runnable runnable;// provides the code to run by using run method within Runnable class
    final long UPDATE = 30; // for the delay to work with handler
    static int spearWidth;
    static int spearHegiht;


    public ViewClass(Context context) {
        super(context);
        this.context = context;
        backView = BitmapFactory.decodeResource(getResources(),R.drawable.game_background);
        spear = BitmapFactory.decodeResource(getResources(), R.drawable.spear);
        Display display = ((Activity )getContext()).getWindowManager().getDefaultDisplay();//create display to get the size of the screen
        Point size = new Point();// create variable size of type point to get x and y of the screen
        display.getSize(size);
        width = size.x;
        heigh = size.y;

        screen = new Rect(0,0,width, heigh);
        dragons = new ArrayList<>();
        bats = new ArrayList<>();
        spears = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            Dragon dragon = new Dragon(context);
            dragons.add(dragon);
            Bat bat = new Bat(context);
            bats.add(bat);

        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        spearWidth = spear.getWidth();
       spearHegiht = spear.getHeight();
       sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
     spearSong = sound.load(context,R.raw.spear_throw,1);
     deadSong = sound.load(context,R.raw.dead,1);

     result = new Paint();
     result.setColor(Color.BLACK);
     result.setTextSize(80);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(backView, null, screen, null);

        for(int x = 0; x < dragons.size(); x++ ){
               // draw the dragin 1
            canvas.drawBitmap(dragons.get(x).getBitmap(), dragons.get(x).dragon_x, dragons.get(x).dragon_y, null);

            dragons.get(x).dragonImage++;

            if(dragons.get(x).dragonImage > 6) dragons.get(x).dragonImage = 0;
               dragons.get(x).dragon_x =  dragons.get(x).dragon_x - dragons.get(x).speed;

               if(dragons.get(x).dragon_x < - dragons.get(x).getWidth()){

                   dragons.get(x).reset();
                   if(score < 0)
                   {
                       Intent intent = new Intent(context, TheEnd.class) ;
                       intent.putExtra("highest score", highestScore);
                       context.startActivity(intent);
                       ((Activity) context).finish();

                   }
               }

        }

            // draw the dragon 2
            canvas.drawBitmap(bats.get(0).getBitmap(),bats.get(0).dragon_x, bats.get(0).dragon_y,null);
            bats.get(0).dragonImage++;

            if(bats.get(0).dragonImage > 3) bats.get(0).dragonImage = 0;

            bats.get(0).dragon_x =   bats.get(0).dragon_x + bats.get(0).speed;

            if(bats.get(0).dragon_x > (width + bats.get(0).getWidth()))
            {

                bats.get(0).reset();
                if(score < 0)
                {
                    Intent intent = new Intent(context, TheEnd.class) ;
                    intent.putExtra("highest score", highestScore);
                    context.startActivity(intent);
                    ((Activity) context).finish();

                }
            }


        for(int i = 0; i < spears.size(); i++)
        {
            if (spears.get(i).y > -spears.get(i).getSpearHeight()) //if the y cordinate is greater than spears hight
            {
                spears.get(i).y -= spears.get(i).speed; // decrease y value of spears by the amount of its veclocity to make it move up
                 // draw the spear
               // canvas.drawBitmap(spears.get(i).spear, spears.get(i).x, spears.get(i).y, null);
                canvas.drawBitmap(spears.get(i).spear,spears.get(i).x, spears.get(i).y, null );
                // the following code to detect the collision between the spear and the dragons

               if (spears.get(i).x >= dragons.get(0).dragon_x + 400  && spears.get(i).x < dragons.get(0).dragon_x + 600 && spears.get(i).y <= dragons.get(0).dragon_y) {
                    dragons.get(0).reset();
                    score++;
                    highestScore++;
                    spears.remove(i);
                    if(deadSong !=0)
                      sound.play(deadSong, 1,1,0,0,1);
                }
                else if (spears.get(i).x >= dragons.get(1).dragon_x + 400  && spears.get(i).x < dragons.get(1).dragon_x + 600 && spears.get(i).y <= dragons.get(1).dragon_y) {
                    dragons.get(1).reset();
                    score++;
                    highestScore++;
                    spears.remove(i);
                  if(deadSong !=0)
                       sound.play(deadSong, 1,1,0,0,1);
                }
                 else if (spears.get(i).x >= bats.get(0).dragon_x -100 && spears.get(i).x < bats.get(0).dragon_x +400 && spears.get(i).y <= bats.get(0).dragon_y) {
                bats.get(0).reset();
                score--;

                spears.remove(i);
                  if(deadSong !=0)
                      sound.play(deadSong, 1,1,0,0,1);
            }
            if(score >= 5) {
                dragons.get(0).speed += 5;
                dragons.get(1).speed += 5;
                //bats.get(0).speed += 5;
                for(int x =0; x < spears.size(); x++) // increase the speed of the spear
                    spears.get(x).speed+=5;

            }


            } else spears.remove(i);
        }
        // to place the spresr on the midle of the background image
        canvas.drawBitmap(spear,(width / 2 - spearWidth/2),(heigh - (spearHegiht)), null );
        canvas.drawText("score: "+ score , 0, 80, result);

        handler.postDelayed(runnable, UPDATE);// this will cause the runnable method to execute
                                             // after the UPDATE time (30 milliseconds)
    }
    // to handle the touch event for the arrow
    @Override
    public boolean onTouchEvent(MotionEvent event)

    {
        float eventX = event.getX();
        float eventY = event.getY();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){

            if(eventX >= (width/2 - spearWidth /2) && eventX <=(width /2 + spearWidth/2)&& eventY>= (heigh -spearHegiht)){


                // create a spear object and add it to spears array list
                if(spears.size() < 2) { Spear s = new Spear(context); spears.add(s);
                if(spearSong !=0)
                    sound.play(spearSong, 1,1,0,0,1);
                }
            }
        }
        return true;
    }
}




